package com.sameh.crm.backend.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HexFormat;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sameh.crm.backend.entities.RefreshToken;
import com.sameh.crm.backend.entities.User;
import com.sameh.crm.backend.repositories.RefreshTokenRepository;
import com.sameh.crm.backend.services.exceptions.RefreshTokenValidationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service 
public class RefreshTokenService {


    @Value(value = "${jwt.refresh.expiration}")
    private long expirationHourse;

    @Value(value="${jwt.refresh.secret}")
    private String secret;

    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository repository){
        this.refreshTokenRepository = repository;
    }

    public String generate(User user){

        Instant currentDate = Instant.now();
        
        Instant expirationInstant = currentDate.plus(expirationHourse, ChronoUnit.HOURS);

        String uuid = UUID.randomUUID().toString(); // refresh token identifier.

        JwtBuilder builder = Jwts.builder();
        
        builder.subject(user.getId());
        
        builder.issuedAt(Date.from(currentDate));

        builder.expiration(Date.from(expirationInstant));

        SecretKey key = getSecretKey();

        builder.signWith(key);

        builder.id(uuid);

        String jwtToken = builder.compact();

        String tokenHash = hashToken(jwtToken);

        RefreshToken refreshToken = new RefreshToken(uuid, tokenHash, expirationInstant, currentDate, null);

        this.refreshTokenRepository.save(refreshToken);

        return jwtToken;

    }


    protected SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    protected String hashToken(String token){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        }catch(NoSuchAlgorithmException ex){
            throw new IllegalStateException("Exception while hashing a token",ex);
        }

    }


    public void validate(String token){

       Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();

       String hash = hashToken(token);

       Optional<RefreshToken> tokenOptional = this.refreshTokenRepository.findByTokenHash(hash);

       RefreshToken existingToken = tokenOptional.orElseThrow(
        ()-> new RefreshTokenValidationException("Wrong refresh token",
            "Couldn't locate the entity using token hash") 
       );


        String tokenId = claims.getId();
        if(!tokenId.equals(existingToken.getId()))
            throw new RefreshTokenValidationException("Invalid refresh token", "Token id (jti) does not match entity.getId()");
        

        String tokenUserId = claims.getSubject();
        if(!tokenUserId.equals(existingToken.getUserId()))
            throw new RefreshTokenValidationException("Invalid refresh token", "Token subject does not match entity.getUserId()");

        
        if(existingToken.getRevokedAt() != null){
            String actualMessage = "Entity token is revoked at :" + existingToken.getRevokedAt().toString();
            throw new RefreshTokenValidationException("Invalid refresh token", actualMessage);
        }

    }

    


}
