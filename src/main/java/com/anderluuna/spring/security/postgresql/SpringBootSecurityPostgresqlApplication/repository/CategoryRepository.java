package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // No es necesario agregar métodos adicionales si solo quieres obtener todas las categorías
}