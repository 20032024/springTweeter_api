package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostreRequest {

    @NotBlank(message = "El nombre del postre no puede estar vacío")
    @Size(max = 100, message = "El nombre del postre no puede tener más de 100 caracteres")
    private String name;  // Nombre del postre

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    private String description;  // Descripción del postre

    private Long categoryId;  // ID de la categoría del postre

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}