package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services.Interfaces;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.PostreReaction;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.PostreReactionRequest;
import java.util.List;
import java.util.Optional;

public interface IPostreReactionService {

    // Crear una reacción a un postre
    PostreReaction createPostreReaction(PostreReactionRequest postreReactionRequest);

    // Obtener reacciones de un postre específico
    List<PostreReaction> getReactionsByPostre(Long postreId);

    // Obtener todas las reacciones de un usuario
    List<PostreReaction> getReactionsByUser(Long userId);

    // Eliminar una reacción
    void deletePostreReaction(Long id);

    // Obtener reacción por ID
    Optional<PostreReaction> getPostreReactionById(Long id);
}