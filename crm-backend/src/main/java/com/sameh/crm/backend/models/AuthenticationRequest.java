package com.sameh.crm.backend.models;

import jakarta.validation.constraints.NotBlank;

public class AuthenticationRequest {

    @NotBlank(message = "Username or email can't be blank")
    private String usernameOrEmail;

    @NotBlank(message = "Password can't be blank")    
    private String password;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest [usernameOrEmail=" + usernameOrEmail + ", password=" + password + "]";
    }

    


}
