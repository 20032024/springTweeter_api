package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Postre;

public interface PostreRepository extends JpaRepository<Postre, Long> {
    List<Postre> findByNameContaining(String name);
    List<Postre> findByCategoryId(Long categoryId);
}