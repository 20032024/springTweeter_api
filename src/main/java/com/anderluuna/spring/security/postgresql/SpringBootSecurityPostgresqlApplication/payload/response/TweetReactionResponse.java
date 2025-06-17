package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

public class TweetReactionResponse {
    private Long id;
    private Long tweetId;
    private Long userId;
    private Long reactionId;

    public TweetReactionResponse(Long id, Long tweetId, Long userId, Long reactionId) {
        this.id = id;
        this.tweetId = tweetId;
        this.userId = userId;
        this.reactionId = reactionId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReactionId() {
        return reactionId;
    }

    public void setReactionId(Long reactionId) {
        this.reactionId = reactionId;
    }
}