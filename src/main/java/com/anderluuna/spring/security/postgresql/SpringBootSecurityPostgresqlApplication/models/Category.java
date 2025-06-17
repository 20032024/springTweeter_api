package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference; // Importar para @JsonManagedReference
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name; // El nombre de la categoría (ejemplo: dulce, salado, etc.)

    // Constructor vacío
    public Category() {
    }

    // Constructor con parámetros si es necesario
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
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
}