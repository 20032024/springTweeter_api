package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Comment;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Tweet;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.CommentRequest;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.CommentRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.TweetRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/all")
    public Page<Comment> getTweet(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @GetMapping("/tweet/{tweetId}")
    public List<Comment> getCommentsByTweetId(@PathVariable Long tweetId) {
        return commentRepository.findByTweetId(tweetId);
    }

    @PostMapping("/create")
    public Comment createComentario(@Valid @RequestBody CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = getValidUser(username);
        Tweet tweet = getValidTweet(commentRequest.getTweetId());

        Comment comentario = new Comment();
        comentario.setContent(commentRequest.getContent());
        comentario.setTweet(tweet);
        comentario.setUser(user);

        return commentRepository.save(comentario);

    }

    private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOpt.get();
    }

    private Tweet getValidTweet(Long tweetId) {
        return tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet no encontrado"));
    }
}