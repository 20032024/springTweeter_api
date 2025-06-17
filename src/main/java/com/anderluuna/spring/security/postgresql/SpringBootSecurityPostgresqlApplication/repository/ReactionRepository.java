package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Reaction;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Tweet;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    
 //   List<Reaction> findByTweet(Tweet tweet);  // Obtener reacciones por tweet
 //   boolean existsByUserIdAndTweetId(Long userId, Long tweetId);  // Verificar si el usuario ya reaccionó
}


