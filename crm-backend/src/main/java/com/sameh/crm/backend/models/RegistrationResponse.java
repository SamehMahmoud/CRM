package com.sameh.crm.backend.models;

import com.sameh.crm.backend.entities.User;

public class RegistrationResponse {

    private String id;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String createdAt;

    public RegistrationResponse(){

    }

    public RegistrationResponse(String id, String username, String email, String phone, String firstName, String lastName, String createdAt) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.id = id;
    }

    public RegistrationResponse(User entity){
        this(entity.getId(), entity.getUsername(), entity.getEmail(),entity.getPhone()
                , entity.getFirstName(),entity.getLastName(), entity.getCreatedAt().toString());
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "RegistrationResponse{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
