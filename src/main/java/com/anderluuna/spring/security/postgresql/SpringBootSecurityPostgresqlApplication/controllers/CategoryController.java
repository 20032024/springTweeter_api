package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response.CategoryResponse;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services.CategoryServiceImpl;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Category;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Postre;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Tweet;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    // Endpoint para obtener todas las categorías
    @GetMapping("/")
    public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryService.getAllCategories();

        // Convertir las categorías a DTOs y asignarles la URL de la imagen
        return categories.stream()
                         .map(category -> {
                             // Asignar una imagen estática a cada categoría según su nombre
                             String imageUrl = "";
                             switch (category.getName().toLowerCase()) {
                                 case "postres de chocolate":
                                     imageUrl = "assets/postre-chocolate.jpg";
                                     break;
                                 case "postres de limón":
                                     imageUrl = "assets/postre-limon.jpg";
                                     break;
                                 case "postres de fresa":
                                     imageUrl = "assets/postre-fresa.jpg";
                                     break;
                                 case "postres de manzana":
                                     imageUrl = "assets/postre-manzana.jpg";
                                     break;
                                 default:
                                     imageUrl = "assets/images/default-category.jpg"; // Imagen por defecto
                                     break;
                             }

                             return new CategoryResponse(
                                 category.getId(),
                                 category.getName(),
                                 category.getPostres().stream()
                                         .map(Postre::getId) // Obtener solo el ID de los postres
                                         .collect(Collectors.toList()), // Convertir a lista de IDs
                                 imageUrl  // Asignar la URL de la imagen
                             );
                         })
                         .collect(Collectors.toList());
    }
}