package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request;

import jakarta.validation.constraints.NotBlank;

public class PostreCommentRequest {

    @NotBlank(message = "El contenido del comentario no puede estar vacío")
    private String content;  // El contenido del comentario

    private Long postreId;  // El ID del postre al que se le comenta

    // Getters y Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostreId() {
        return postreId;
    }

    public void setPostreId(Long postreId) {
        this.postreId = postreId;
    }
}