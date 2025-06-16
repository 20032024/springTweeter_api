package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.PostreReactionRequest;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response.PostreReactionResponse;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.PostreReaction;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services.PostreReactionServiceImpl;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reactions")
public class PostreReactionController {

    @Autowired
    private PostreReactionServiceImpl postreReactionService;

    @Autowired
    private UserRepository userRepository;

    // Endpoint para crear una reacción (like) a un postre
    @PostMapping("/like")
    public PostreReactionResponse createLike(@RequestBody PostreReactionRequest postreReactionRequest) {
        // Obtener el usuario autenticado
        String username = SecurityContextHolder.getContext().getAuthentication().getName(); 

        // Obtener el usuario desde la base de datos usando el nombre de usuario
        User user = getValidUser(username);

        // Crear una reacción (like) a un postre
        PostreReaction postreReaction = postreReactionService.createPostreReaction(postreReactionRequest);

        // Crear y devolver el response con los detalles de la reacción
        return new PostreReactionResponse(
                postreReaction.getId(),
                postreReaction.getPostre().getId(),
                postreReaction.getReaction().getId(),
                postreReaction.getUser().getId()
        );
    }

    // Endpoint para obtener las reacciones de un postre
    @GetMapping("/{postreId}")
    public List<PostreReaction> getReactionsByPostre(@PathVariable Long postreId) {
        return postreReactionService.getReactionsByPostre(postreId);
    }

    // Endpoint para obtener todas las reacciones de un usuario
    @GetMapping("/user/{userId}")
    public List<PostreReaction> getReactionsByUser(@PathVariable Long userId) {
        return postreReactionService.getReactionsByUser(userId);
    }

    // Método para obtener el usuario autenticado
    private User getValidUser(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return userOpt.get();
    }
}