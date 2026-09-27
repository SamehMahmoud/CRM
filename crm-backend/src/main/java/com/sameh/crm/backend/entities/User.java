package com.sameh.crm.backend.entities;


import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "APP_USER")
public class User {


//    public static enum UserStatus {
//        ACTIVE, SUSPENDED;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;

    @Column(name ="name", nullable = false)
    private String name;

    @Column(name ="phone", nullable = true)
    private String phone;

    @Column(name ="email", nullable = false, unique = true)
    private String email;

//    @Column(name="status" , nullable = false)
//    @Enumerated(EnumType.STRING)
//    private UserStatus status;

//    @Column(name="created_at" , nullable = false)
//    private Instant createdAt;

    public User() {

    }

    public User(String name, String phone, String email) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

//    public UserStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(UserStatus status) {
//        this.status = status;
//    }

//    public Instant getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Instant createdAt) {
//        this.createdAt = createdAt;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }



}
