package com.mypackage.servesmart.dto;
public class LoginRequest {
    private String email;
    private String password;
    private String role; // This will be ignored

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
