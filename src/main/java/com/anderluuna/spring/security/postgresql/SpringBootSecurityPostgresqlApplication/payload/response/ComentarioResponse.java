package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

import java.time.LocalDateTime;

public class ComentarioResponse {
    private Long id;
    private String contenido;
    private UserResponse user;
    private LocalDateTime fechaCreacion;

    // Constructor
    public ComentarioResponse(Long id, String contenido, UserResponse user, LocalDateTime fechaCreacion) {
        this.id = id;
        this.contenido = contenido;
        this.user = user;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters

}