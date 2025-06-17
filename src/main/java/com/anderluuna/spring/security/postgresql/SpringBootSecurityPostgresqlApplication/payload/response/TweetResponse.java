package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

public class TweetResponse {
    private Long id;
    private String contenido;
    private PostreResponse postre;

    // Constructor
    public TweetResponse(Long id, String contenido, PostreResponse postre) {
        this.id = id;
        this.contenido = contenido;
        this.postre = postre;
    }

    // Getters y Setters
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

    public PostreResponse getPostre() {
        return postre;
    }

    public void setPostre(PostreResponse postre) {
        this.postre = postre;
    }
}