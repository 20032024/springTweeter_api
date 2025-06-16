package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.PostreComment;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.PostreCommentRequest;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.PostreCommentRepository;
import java.util.List;

@Service
public class PostreCommentServiceImpl {

    @Autowired
    private PostreCommentRepository postreCommentRepository;

    // Crear un comentario para un postre
    public PostreComment createPostreComment(PostreComment postreComment) {
        return postreCommentRepository.save(postreComment);
    }

    // Obtener todos los comentarios de un postre
    public List<PostreComment> getCommentsByPostre(Long postreId) {
        return postreCommentRepository.findByPostreId(postreId);
    }

    // Eliminar un comentario
    public void deletePostreComment(Long id) {
        postreCommentRepository.deleteById(id);
    }
}