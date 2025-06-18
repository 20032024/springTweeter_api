package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 140)
    private String tweet; // Contenido del tweet (comentarios sobre el postre)

    @ManyToOne(fetch = FetchType.EAGER) // Cambiar a EAGER
    @JoinColumn(name = "posted_by", referencedColumnName = "id")
    private User postedBy;

    @OneToMany(mappedBy = "tweet")
    @JsonIgnore
    private Set<TweetReaction> likes = new HashSet<>();

    // Nuevos campos
    @Size(max = 500)
    private String ingredientes;

    @Size(max = 50)
    private String namePostre;

    @Size(max = 50)
    private String tipoPostre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category categoria;

    // Constructor vacío
    public Tweet() {
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public Set<TweetReaction> getLikes() {
        return likes;
    }

    public void setLikes(Set<TweetReaction> likes) {
        this.likes = likes;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getNamePostre() {
        return namePostre;
    }

    public void setNamePostre(String namePostre) {
        this.namePostre = namePostre;
    }

    public String getTipoPostre() {
        return tipoPostre;
    }

    public void setTipoPostre(String tipoPostre) {
        this.tipoPostre = tipoPostre;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }
}