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
    private UUID id;

    @ManyToOne
    @JoinColumn(name="tenant_id", nullable = false)
    private Tenant tenant;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private CustomerType type;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="created_by", nullable = false)
    private User createdBy;

    @Column(name="created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private List<ContactPoint> contactPoints;

    public Customer() {
    }

    public Customer(Tenant tenant, CustomerType type, String name, User createdBy) {
        this(tenant, type, name, createdBy, Instant.now());
    }

    public Customer(Tenant tenant, CustomerType type, String name, User createdBy, Instant createdAt) {
        this.tenant = tenant;
        this.type = type;
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", tenant=" + tenant +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", contactPoints=" + contactPoints +
                '}';
    }
}
