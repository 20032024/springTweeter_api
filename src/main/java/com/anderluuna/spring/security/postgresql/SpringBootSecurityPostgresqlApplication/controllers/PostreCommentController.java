package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Postre;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.PostreComment;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services.PostreCommentServiceImpl;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.PostreCommentRequest;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response.PostreCommentResponse;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.PostreRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class PostreCommentController {

    @Autowired
    private PostreCommentServiceImpl postreCommentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostreRepository postreRepository;

@PostMapping("/create")
public PostreCommentResponse createComment(@RequestBody PostreCommentRequest postreCommentRequest) {
    // Obtener el usuario autenticado
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName(); // Obtener nombre de usuario desde el contexto

    // Obtener el usuario desde la base de datos usando el nombre de usuario
    User user = getValidUser(username);

    // Buscar el postre por su ID
    Postre postre = postreRepository.findById(postreCommentRequest.getPostreId())
            .orElseThrow(() -> new RuntimeException("Postre no encontrado"));

    // Crear la entidad de PostreComment desde el DTO
    PostreComment postreComment = new PostreComment();
    postreComment.setContent(postreCommentRequest.getContent());
    postreComment.setPostre(postre);  // Asocia el postre al comentario
    postreComment.setUser(user);  // Asocia el usuario autenticado

    // Guardar el comentario utilizando el servicio
    postreComment = postreCommentService.createPostreComment(postreComment);

    // Crear la respuesta, incluyendo el nombre del usuario
    return new PostreCommentResponse(
        postreComment.getId(),
        postreComment.getContent(),
        postreComment.getPostre().getId(),
        postreComment.getUser().getUsername(), // Obtener el nombre del usuario desde el objeto 'User'
        postreComment.getUser().getId()
    );
}
    // Obtener comentarios de un postre
    @GetMapping("/{postreId}")
    public List<PostreComment> getComments(@PathVariable Long postreId) {
        return postreCommentService.getCommentsByPostre(postreId);
    }

    // Eliminar un comentario
    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        postreCommentService.deletePostreComment(id);
        System.out.println("Comentario eliminado.");
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