package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

public class PostreResponse {

    private Long id;
    private String name;  // Nombre del postre
    private String description;  // Descripción del postre
    private Long categoryId;  // ID de la categoría

    // Constructor
    public PostreResponse(Long id, String name, String description, Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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