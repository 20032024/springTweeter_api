package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "tweet_reactions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "tweet_id"})
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TweetReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;  // El usuario que reaccionó al tweet

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tweet_id", referencedColumnName = "id")
    private Tweet tweet;  // El tweet al que se reaccionó

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reaction_id", referencedColumnName = "id")
    private Reaction reaction;  // Tipo de reacción (like, love, etc.)

    // Constructor vacío
    public TweetReaction() {}

    // Constructor con los parámetros
    public TweetReaction(User user, Tweet tweet, Reaction reaction) {
        this.user = user;
        this.tweet = tweet;
        this.reaction = reaction;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }
}