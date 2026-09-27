package com.sameh.crm.backend.entities;


import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "APP_USER")
public class User {

    public static enum UserStatus {
        ACTIVE, SUSPENDED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;

    @Column(name ="username", nullable = false)
    private String username;

    @Column(name="first_name", nullable = true)
    private String firstName;

    @Column(name="last_name" , nullable = true)
    private String lastName;

    @Column(name ="phone", nullable = true)
    private String phone;

    @Column(name ="email", nullable = false, unique = true)
    private String email;

    @Column(name="status" , nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name="created_at" , nullable = false)
    private Instant createdAt;

    public User() {

    }

    public User(String name, String phone, String email) {
        this.username = name;
        this.phone = phone;
        this.email = email;
//        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName){this.firstName=firstName}

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ",firstName='" + firstName + '\''+
                ",lastName='" + lastName + '\''+
                ",status='" + status + '\''+
                ",createdAt='" + createdAt + '\''+
                '}';
    }



}
