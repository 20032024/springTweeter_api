package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services.Interfaces;

import java.util.List;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Postre;

public interface IPostre {
    Postre createPostre(Postre postre);
    Postre getPostreById(Long id);
    List<Postre> getAllPostres();
    Postre updatePostre(Long id, Postre postre);
    void deletePostre(Long id);
}