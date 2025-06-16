package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.PostreComment;

import java.util.List;

@Repository
public interface PostreCommentRepository extends JpaRepository<PostreComment, Long> {

    // Método para obtener los comentarios de un postre específico
    List<PostreComment> findByPostreId(Long postreId);

    // Guardar el comentario del postre
    PostreComment save(PostreComment postreComment);
}