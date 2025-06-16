package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

public class PostreCommentResponse {

    private Long id;
    private String content;
    private Long postreId;  // El ID del postre al que se le ha agregado el comentario
    private String nameUser;
    private Long userId;    // El ID del usuario que hizo el comentario

    // Constructor
    public PostreCommentResponse(Long id, String content, Long postreId, String namerUser, Long userId) {
        this.id = id;
        this.content = content;
        this.postreId = postreId;
        this.nameUser = namerUser;
        this.userId = userId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    
}