package com.sameh.crm.backend.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name="CONTACT_POINT")
public class ContactPoint {

    public static enum ContactPointType {
        PHONE, EMAIL, WHATSAPP
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", nullable = false)
    private String id;

    @Column(name="customer_id", nullable = false)
    private String customerId; // no need for the whole Customer object

    @Column(name="tenant_id", nullable = false)
    private String tenantId; // no need for the whole Tenant object

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private ContactPointType type;

    @Column(name="contact_value", nullable = false)
    private String contactValue;

    @Column(name="normalized_value", nullable = false)
    private String normalizedValue;

    @Column(name="created_by", nullable = false)
    private String createdBy; // No need to load the entire user, the id is enough

    @Column(name = "created_at" , nullable = false)
    private Instant createdAt;

    public ContactPoint() {
    }

    public ContactPoint(String customerId, String tenantId, ContactPointType type, String contactValue,
                        String normalizedValue, String createdBy, Instant createdAt) {
        this.customerId = customerId;
        this.tenantId = tenantId;
        this.type = type;
        this.contactValue = contactValue;
        this.normalizedValue = normalizedValue;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public ContactPointType getType() {
        return type;
    }

    public void setType(ContactPointType type) {
        this.type = type;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    public String getNormalizedValue() {
        return normalizedValue;
    }

    public void setNormalizedValue(String normalizedValue) {
        this.normalizedValue = normalizedValue;
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
        return "ContactPoint{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", type=" + type +
                ", contactValue='" + contactValue + '\'' +
                ", normalizedValue='" + normalizedValue + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
