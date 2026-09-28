package com.sameh.crm.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name="USER_TENANT")
public class UserTenant {

    public UserTenant() {
    }

    public UserTenant(User user, Tenant tenant, String role) {
        this.user = user;
        this.tenant = tenant;
        this.role = role;
        this.id = new UserTenantCompositeId();
        this.id.setTenantId(this.tenant.getId());
        this.id.setUserId(this.user.getId());
    }

    @EmbeddedId
    private UserTenantCompositeId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("tenantId")
    @JoinColumn(name="tenant_id", nullable = false)
    private Tenant tenant;

    @Column(name="role", nullable = false)
    private String role;

    public UserTenantCompositeId getId() {
        return id;
    }

    public void setId(UserTenantCompositeId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
