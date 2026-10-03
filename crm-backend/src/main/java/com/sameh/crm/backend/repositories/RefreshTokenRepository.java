package com.sameh.crm.backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sameh.crm.backend.entities.RefreshToken;

@Repository 
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    public Optional<RefreshToken> findByTokenHash(String hash);

}
