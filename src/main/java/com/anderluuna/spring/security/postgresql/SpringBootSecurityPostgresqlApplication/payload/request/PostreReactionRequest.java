package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request;

public class PostreReactionRequest {

    private Long postreId;  // El ID del postre al que se va a reaccionar
    private Long reactionId; // El ID de la reacción (like, love, etc.)

    // Getters y Setters
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
}