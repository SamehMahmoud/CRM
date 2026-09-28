package com.sameh.crm.backend.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name="TENANT")
public class Tenant {

    public enum TenantStatus{
        ACTIVE, INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private TenantStatus status;

    @Column(name="created_at", nullable = false)
    private Instant createdAt;

    @Column(name="created_by", nullable = false)
    private String createdBy;

    public Tenant() {
    }

    public Tenant(String name, String email, String phone) {
        this(name,email,phone,TenantStatus.ACTIVE, Instant.now());
    }

    public Tenant(String name, String email, String phone, TenantStatus status, Instant instant) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.createdAt= instant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TenantStatus getStatus() {
        return status;
    }

    public void setStatus(TenantStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", createBy='" + createdBy + '\'' +
                '}';
    }
}
