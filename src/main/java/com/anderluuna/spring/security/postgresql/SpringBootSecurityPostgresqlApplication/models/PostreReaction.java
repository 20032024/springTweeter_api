package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import jakarta.persistence.*;

@Entity
@Table(name = "postre_reactions")
public class PostreReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;  // El usuario que reaccionó al postre

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postre_id", referencedColumnName = "id")
    private Postre postre;  // El postre sobre el que se reaccionó

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reaction_id", referencedColumnName = "id")
    private Reaction reaction;  // El tipo de reacción (Ej. like, love, etc.)

    // Constructor vacío
    public PostreReaction() {}

    // Constructor con los parámetros
    public PostreReaction(User user, Postre postre, Reaction reaction) {
        this.user = user;
        this.postre = postre;
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

    public Postre getPostre() {
        return postre;
    }

    public void setPostre(Postre postre) {
        this.postre = postre;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }
}