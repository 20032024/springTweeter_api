package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Category;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long categoriaId);

}