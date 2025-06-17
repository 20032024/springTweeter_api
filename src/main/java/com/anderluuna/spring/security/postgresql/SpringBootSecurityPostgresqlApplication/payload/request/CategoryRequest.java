package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request;

import java.util.List;

public class CategoryRequest {

    private Long id;
    private String name;
    private List<Long> postreIds;  // IDs de los postres que pertenecen a esta categoría

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
}