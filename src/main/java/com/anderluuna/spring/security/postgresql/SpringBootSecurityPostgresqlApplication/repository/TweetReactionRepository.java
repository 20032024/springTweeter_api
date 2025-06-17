package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.TweetReaction;

@Repository
public interface TweetReactionRepository extends JpaRepository<TweetReaction, Long> {
//    boolean existsByUser_IdAndTweet_Id(Long userId, Long tweetId);
}

