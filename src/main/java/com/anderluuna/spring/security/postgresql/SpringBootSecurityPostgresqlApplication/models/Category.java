package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;  // Nombre de la categoría (Ej. "Dulces", "Frutales", etc.)

    @Column(nullable = true, length = 255)
    private String imageUrl;  // URL de la imagen asociada a la categoría

    @OneToMany(mappedBy = "category")
    private Set<Postre> postres = new HashSet<>();  // Relación con los postres que pertenecen a esta categoría

    // Constructor vacío
    public Category() {}

    // Constructor con el nombre de la categoría y la URL de la imagen
    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Postre> getPostres() {
        return postres;
    }

    public void setPostres(Set<Postre> postres) {
        this.postres = postres;
    }
}