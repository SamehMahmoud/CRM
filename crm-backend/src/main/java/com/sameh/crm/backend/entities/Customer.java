package com.sameh.crm.backend.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="CUSTOMER")
public class Customer {

    public enum CustomerType{
        INDIVIDUAL, ORGANIZATION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", nullable = false)
    private String id;

//    @ManyToOne
//    @JoinColumn(name="tenant_id", nullable = false)
//    private Tenant tenant;

    @Column(name="tenant_id", nullable = false)
    private String tenantId;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private CustomerType type;

    @Column(name="name", nullable = false)
    private String name;

//    @ManyToOne
//    @JoinColumn(name="created_by", nullable = false)
//    private User createdBy;

    @Column(name="created_by", nullable = false)
    private String createdBy;

    @Column(name="created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="customer_id")
    private List<ContactPoint> contactPoints;

    public Customer() {
    }

    public Customer(String tenantId, CustomerType type, String name, String userId) {
        this(tenantId, type, name, userId, Instant.now());
    }

    public Customer(String tenantId, CustomerType type, String name, String userId, Instant createdAt) {
        this.tenantId = tenantId;
        this.type = type;
        this.name = name;
        this.createdBy = userId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<ContactPoint> getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(List<ContactPoint> contactPoints) {
        this.contactPoints = contactPoints;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", tenantId=" + tenantId +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", contactPoints=" + contactPoints +
                '}';
    }
}
