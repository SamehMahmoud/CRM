package com.sameh.crm.backend.models;

import jakarta.validation.constraints.*;


public class RegistrationRequest {

    @Size(max = 50, min=2)
    @NotBlank(message = "Username can't be blank")
    private String username;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email can't be blank")
    private String email;

    @NotBlank(message = "Password can't be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$",
            message = "Password must contain at least 8 characters, including uppercase, lowercase, digit and special character"
    )
    private String plainTextPassword;

    @NotBlank
    @Size(max = 50, min=3)
    private String firstName;

    @NotBlank
    @Size(max = 50, min=3)
    private String lastName;

    @NotBlank
    @Size(max = 50, message = "Phone can't exceed 50 characters")
    private String phone;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String username, String email, String plainTextPassword, String firstName, String lastName, String phone) {
        this.username = username;
        this.email = email;
        this.plainTextPassword = plainTextPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
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

    public String getPlainTextPassword() {
        return plainTextPassword;
    }

    public void setPlainTextPassword(String plainTextPassword) {
        this.plainTextPassword = plainTextPassword;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", plainTextPassword='" + plainTextPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
