package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

public class UserResponse {
    private Long id;
    private String username;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}