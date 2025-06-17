package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import com.fasterxml.jackson.annotation.JsonBackReference;  // Importar para @JsonBackReference
import jakarta.persistence.*;

@Entity
@Table(name = "postres")
public class Postre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference  // Evita la recursión en el lado de Postre
    private Category category;  // Relación con la categoría

    // Constructor vacío
    public Postre() {}

    // Constructor con parámetros
    public Postre(String name, Category category) {
        this.name = name;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}