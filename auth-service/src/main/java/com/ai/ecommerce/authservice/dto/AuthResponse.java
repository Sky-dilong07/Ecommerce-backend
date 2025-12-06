package com.ai.ecommerce.authservice.dto;

public class AuthResponse {
    private String message;
    private String token;
    private String refreshToken;

    public AuthResponse(String message) {
        this.message = message;
    }

    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public AuthResponse(String message, String token, String refreshToken) {
        this.message = message;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    // Getters & Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
}
