package com.example.spring_boot_token.payload.request;

public class LogoutRequest {
    private Long userId;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
} 