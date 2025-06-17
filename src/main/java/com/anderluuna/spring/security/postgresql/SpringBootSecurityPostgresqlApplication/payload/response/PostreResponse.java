package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

public class PostreResponse {

    private Long id;
    private String nombre;
    private CategoryResponse categoria;

    // Constructor
    public PostreResponse(Long id, String nombre, CategoryResponse categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoryResponse getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoryResponse categoria) {
        this.categoria = categoria;
    }


}