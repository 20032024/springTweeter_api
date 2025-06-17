package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services.Interfaces;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Category;
import java.util.List;

public interface ICategory {
    
    // Método para obtener todas las categorías
    List<Category> getAllCategories();
}