package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Postre;
import java.util.Optional;

public interface PostreRepository extends JpaRepository<Postre, Long> {
    Optional<Postre> findByName(String name);  // Cambié esto a Optional<Postre>
}