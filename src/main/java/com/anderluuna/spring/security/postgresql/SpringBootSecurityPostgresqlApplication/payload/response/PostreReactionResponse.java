package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

public class PostreReactionResponse {

    private Long id;
    private Long postreId;
    private Long reactionId;
    private Long userId;  // El ID del usuario que reaccionó

    // Constructor
    public PostreReactionResponse(Long id, Long postreId, Long reactionId, Long userId) {
        this.id = id;
        this.postreId = postreId;
        this.reactionId = reactionId;
        this.userId = userId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostreId() {
        return postreId;
    }

    public void setPostreId(Long postreId) {
        this.postreId = postreId;
    }

    public Long getReactionId() {
        return reactionId;
    }

    public void setReactionId(Long reactionId) {
        this.reactionId = reactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}