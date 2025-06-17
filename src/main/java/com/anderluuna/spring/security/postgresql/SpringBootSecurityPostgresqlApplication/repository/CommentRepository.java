package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTweetId(Long tweetId);
}
