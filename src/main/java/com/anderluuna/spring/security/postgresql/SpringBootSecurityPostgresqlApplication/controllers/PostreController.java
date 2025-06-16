package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Postre;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response.PostreResponse;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.PostreRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.PostreRequest;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Category;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.CategoryRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/postres")
public class PostreController {

    @Autowired
    private PostreRepository postreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Crear un postre
    @PostMapping("/create")
    public PostreResponse createPostre(@RequestBody PostreRequest postreRequest) {
        
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // Obtener el usuario desde la base de datos usando el nombre de usuario
        User user = getValidUser(username);
        
        // Obtener la categoría desde la base de datos
        Category category = getValidCategory(postreRequest.getCategoryId());

        // Crear un nuevo postre y asociarlo al usuario y categoría
        Postre postre = new Postre(postreRequest.getName(), postreRequest.getDescription(), category, user);
        
        // Guardar el postre en la base de datos
        postre = postreRepository.save(postre);

        // Devolver el DTO de respuesta con la información del postre
        return new PostreResponse(postre.getId(), postre.getName(), postre.getDescription(), postre.getCategory().getId());
    }

    // Método para obtener el usuario autenticado
    private User getValidUser(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return userOpt.get();
    }

    // Método para obtener la categoría por ID
    private Category getValidCategory(Long categoryId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if (!categoryOpt.isPresent()) {
            throw new RuntimeException("Categoría no encontrada");
        }
        return categoryOpt.get();
    }
}