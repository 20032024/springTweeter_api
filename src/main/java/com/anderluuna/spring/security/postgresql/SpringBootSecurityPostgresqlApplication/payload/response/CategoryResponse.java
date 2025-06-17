package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

import java.util.List;

public class CategoryResponse {

    private Long id;
    private String name;
    private List<Long> postreIds;  // Lista de IDs de postres asociados a la categoría
    private String imageUrl;  // URL de la imagen asociada a la categoría

    // Constructor
    public CategoryResponse(Long id, String name, List<Long> postreIds, String imageUrl) {
        this.id = id;
        this.name = name;
        this.postreIds = postreIds;
        this.imageUrl = imageUrl;
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

    public List<Long> getPostreIds() {
        return postreIds;
    }

    public void setPostreIds(List<Long> postreIds) {
        this.postreIds = postreIds;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}