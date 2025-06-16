package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import jakarta.persistence.*;

@Entity
@Table(name = "postre_comments")
public class PostreComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;  // El usuario que comentó sobre el postre

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postre_id", referencedColumnName = "id")
    private Postre postre;  // El postre sobre el cual se hizo el comentario

    private String content;  // El contenido del comentario

    // Constructor vacío
    public PostreComment() {}

    // Constructor con los parámetros
    public PostreComment(User user, Postre postre, String content) {
        this.user = user;
        this.postre = postre;
        this.content = content;
    }

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

    public Postre getPostre() {
        return postre;
    }

    public void setPostre(Postre postre) {
        this.postre = postre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getters y Setters

}