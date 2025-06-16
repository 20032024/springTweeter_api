package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.PostreReaction;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.PostreReactionRequest;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.PostreReactionRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Postre;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Reaction;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.PostreRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.ReactionRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services.Interfaces.IPostreReactionService;

import java.util.List;
import java.util.Optional;

@Service
public class PostreReactionServiceImpl implements IPostreReactionService {

    @Autowired
    private PostreReactionRepository postreReactionRepository;

    @Autowired
    private PostreRepository postreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReactionRepository reactionRepository;  // Inyectar el repositorio de reacciones

    @Override
    public PostreReaction createPostreReaction(PostreReactionRequest postreReactionRequestDTO) {
        // Obtener el usuario autenticado
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Obtener el usuario desde la base de datos usando el nombre de usuario
        User user = getValidUser(username);

        // Obtener el postre desde la base de datos
        Postre postre = postreRepository.findById(postreReactionRequestDTO.getPostreId())
                .orElseThrow(() -> new RuntimeException("Postre no encontrado"));

        // Obtener la reacción desde la base de datos
        Reaction reaction = reactionRepository.findById(postreReactionRequestDTO.getReactionId())
                .orElseThrow(() -> new RuntimeException("Reacción no encontrada"));

        // Crear la entidad PostreReaction
        PostreReaction postreReaction = new PostreReaction();
        postreReaction.setPostre(postre);
        postreReaction.setReaction(reaction);  // Asignar la entidad Reaction
        postreReaction.setUser(user);

        // Guardar la reacción en la base de datos
        return postreReactionRepository.save(postreReaction);
    }

    @Override
    public List<PostreReaction> getReactionsByPostre(Long postreId) {
        return postreReactionRepository.findAllByPostreId(postreId);
    }

    @Override
    public List<PostreReaction> getReactionsByUser(Long userId) {
        return postreReactionRepository.findAllByUserId(userId);
    }

    @Override
    public void deletePostreReaction(Long id) {
        postreReactionRepository.deleteById(id);
    }

    @Override
    public Optional<PostreReaction> getPostreReactionById(Long id) {
        return postreReactionRepository.findById(id);
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