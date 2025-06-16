package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    

}