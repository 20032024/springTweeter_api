package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Category;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.CategoryRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services.Interfaces.ICategory;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategory {

    @Autowired
    private CategoryRepository categoryRepository;

    // Implementación del método de la interfaz ICategory
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();  // Recupera todas las categorías de la base de datos
    }
}