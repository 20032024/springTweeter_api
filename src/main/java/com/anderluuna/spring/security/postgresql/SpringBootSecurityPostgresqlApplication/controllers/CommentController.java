package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Comment;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Tweet;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.CommentRequest;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response.ComentarioResponse;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.CommentRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.TweetRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response.UserResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/all")
    public List<ComentarioResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAll(); // Obtener los comentarios desde la base de datos
        List<ComentarioResponse> responses = comments.stream()
                .map(this::mapToComentarioResponse) // Convertirlos en la forma adecuada para la respuesta
                .collect(Collectors.toList());

        return responses;
    }

    public ComentarioResponse mapToComentarioResponse(Comment comment) {
        ComentarioResponse response = new ComentarioResponse();
        response.setId(comment.getId());
        response.setContenido(comment.getContent());

        // Mapear el usuario a UserResponse
        UserResponse userResponse = new UserResponse();
        userResponse.setId(comment.getUser().getId());
        userResponse.setUsername(comment.getUser().getUsername());
        response.setUser(userResponse);

        response.setFechaCreacion(comment.getFechaCreacion());
        return response;
    }

    @GetMapping("/tweet/{tweetId}/comments")
    public List<ComentarioResponse> getCommentsByTweetId(@PathVariable Long tweetId) {
        // Obtener los comentarios del tweet específico
        List<Comment> comments = commentRepository.findByTweetId(tweetId);

        // Convertir los comentarios en el formato adecuado para la respuesta
        return comments.stream()
                .map(this::mapToComentarioResponse) // Mapear a ComentarioResponse
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public Comment createComentario(@Valid @RequestBody CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Obtener el usuario autenticado (este sería el que haga el comentario)
        User user = getValidUser(username);
        // Asegúrate de que el userId no sea nulo y que exista
        if (commentRequest.getUserId() != null && !user.getId().equals(commentRequest.getUserId())) {
            throw new RuntimeException("User ID does not match the authenticated user");
        }

        Tweet tweet = getValidTweet(commentRequest.getTweetId());

        Comment comentario = new Comment();
        comentario.setContent(commentRequest.getContent());
        comentario.setTweet(tweet);
        comentario.setUser(user); // Aquí asignamos el usuario correcto

        return commentRepository.save(comentario);
    }

    // Método para obtener el usuario por el username (asegúrate de que el userId es
    // válido)
    private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOpt.get();
    }

    // Método para obtener el tweet por ID (asegúrate de que el tweetId es válido)
    private Tweet getValidTweet(Long tweetId) {
        return tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
    }
}
