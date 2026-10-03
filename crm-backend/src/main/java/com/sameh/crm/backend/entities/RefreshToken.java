package com.sameh.crm.backend.entities;

import java.time.Instant;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="REFRESH_TOKEN")
public class RefreshToken {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="user_id", nullable = false)
    private String userId;

    @Column(name="token_hash", unique = true, nullable = false)
    private String tokenHash;

    @Column(name="expires_at", nullable = false)
    private Instant expiresAt;
    
    @Column(name="created_at", nullable = false)
    private Instant createdAt;
    
    @Column(name="revoked_at", nullable = true)
    private Instant revokedAt;
    
    
    public RefreshToken() {
    }


    public RefreshToken(String userId, String tokenHash, Instant expiresAt, Instant createdAt, Instant revokedAt) {
        this.userId = userId;
        this.tokenHash = tokenHash;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.revokedAt = revokedAt;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTokenHash() {
        return tokenHash;
    }
    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }
    public Instant getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public Instant getRevokedAt() {
        return revokedAt;
    }
    public void setRevokedAt(Instant revokedAt) {
        this.revokedAt = revokedAt;
    }


    @Override
    public String toString() {
        return "RefreshToken [id=" + id + ", userId=" + userId + ", token_hash=" + tokenHash + ", expiresAt="
                + expiresAt + ", createdAt=" + createdAt + ", revokedAt=" + revokedAt + "]";
    }


    

}
