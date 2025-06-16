package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "postres")
public class Postre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;  // Nombre del postre (ej. "Pastel de Chocolate")

    @Column(nullable = false, length = 500)
    private String description;  // Descripción del postre (ej. "Un delicioso pastel de chocolate...")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;  // Relacionado con la categoría del postre

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by", referencedColumnName = "id")
    private User postedBy;  // El usuario que publicó el postre

    @OneToMany(mappedBy = "postre", cascade = CascadeType.ALL)
    private Set<PostreReaction> reactions = new HashSet<>();  // Reacciones (likes, love, etc.)

    @OneToMany(mappedBy = "postre", cascade = CascadeType.ALL)
    private Set<PostreComment> comments = new HashSet<>();  // Comentarios sobre el postre

    // Constructor vacío
    public Postre() {}

    // Constructor con parámetros
    public Postre(String name, String description, Category category, User postedBy) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.postedBy = postedBy;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public Set<PostreReaction> getReactions() {
        return reactions;
    }

    public void setReactions(Set<PostreReaction> reactions) {
        this.reactions = reactions;
    }

    public Set<PostreComment> getComments() {
        return comments;
    }

    public void setComments(Set<PostreComment> comments) {
        this.comments = comments;
    }
}