package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 500)
    private String content; // El contenido del comentario

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER para cargar inmediatamente
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // El usuario que hizo el comentario

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER
    @JoinColumn(name = "tweet_id", referencedColumnName = "id")
    private Tweet tweet;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now(); // La fecha en que se creó el comentario

    // Constructor vacío
    public Comment() {
    }

    // Constructor con parámetros
    public Comment(String content, User user, Tweet tweet) {
        this.content = content;
        this.user = user;
        this.tweet = tweet;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}