package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.PostreReaction;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.PostreReactionRequest;

@Repository
public interface PostreReactionRepository extends JpaRepository<PostreReaction, Long> {
    // Puedes agregar consultas personalizadas si es necesario
     List<PostreReaction> findByUserId(Long userId);

     // obtener reaciones de un postre especifico
     List<PostreReaction> findAllByPostreId(Long postreId);

     // Buscar usuarios que reaccionarion
     List<PostreReaction> findAllByUserId(Long userId);

     PostreReaction save(PostreReactionRequest postreReactionRequestDTO);
}