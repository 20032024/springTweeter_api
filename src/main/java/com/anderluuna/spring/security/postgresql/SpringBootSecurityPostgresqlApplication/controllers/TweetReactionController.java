package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Tweet;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.TweetReaction;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.TweetReactionRequest;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response.TweetReactionResponse;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Reaction;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.ReactionRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.TweetReactionRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.TweetRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reactions")

public class TweetReactionController {

    @Autowired
    private TweetReactionRepository tweetReactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private ReactionRepository reactionRepository;


@GetMapping("/all")
public Page<TweetReactionResponse> getTweet(Pageable pageable) {
    // Obtener todas las reacciones de tweet (entidades)
    Page<TweetReaction> reactions = tweetReactionRepository.findAll(pageable);

    // Convertir cada TweetReaction en un TweetReactionResponse
    Page<TweetReactionResponse> reactionsResponse = reactions.map(reaction -> {
        return new TweetReactionResponse(
            reaction.getId(),               // ID de la reacción
            reaction.getTweet().getId(),     // ID del tweet
            reaction.getUser().getId(),      // ID del usuario
            reaction.getReaction().getId()   // ID de la reacción
        );
    });

    // Retornar la lista de respuestas
    return reactionsResponse;
}
  @PostMapping("/create")
 
  public TweetReaction createReaction(@Valid @RequestBody TweetReactionRequest tweetReaction) {
    
        System.out.println("tweetid : " + tweetReaction.getTweetId()  );
        System.out.println("reactiontid : " + tweetReaction.getReactionId()  );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        System.out.println("userid : " + userId  );


        User user = getValidUser(userId);
        System.out.println("user");

        System.out.println(user);

        TweetReaction myTweetReaction = new TweetReaction();
        Tweet myTweet = getValidTweet(tweetReaction.getTweetId());
        System.out.println("object tweet : " );
        System.out.println(myTweet );


        Reaction myReaction = getValidReaction(tweetReaction.getReactionId());
        System.out.println("object reaction : "   );
        System.out.println( myReaction );

        //myTweetReaction.setUserId(user.getId());
        //myTweetReaction.setTweetId(myTweet.getId());
        //myTweetReaction.setReactionId(myReaction.getId());
        
        myTweetReaction.setUser(user);
        myTweetReaction.setTweet(myTweet);
        myTweetReaction.setReaction(myReaction);
        
        System.out.println("tweet reaction : "   );
        System.out.println( myTweetReaction.getId());
                System.out.println( myTweetReaction.getTweet());

                        System.out.println( myTweetReaction.getUser());


        tweetReactionRepository.save(myTweetReaction);

        return myTweetReaction;
  }

    private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOpt.get();
    }

    private Tweet getValidTweet(Long tweetId) {
        Optional<Tweet> tweetOpt = tweetRepository.findById(tweetId);
        if (!tweetOpt.isPresent()) {
            throw new RuntimeException("Tweet not found");
        }
        return tweetOpt.get();
    }

    private Reaction getValidReaction(Long reactionId) {
        Optional<Reaction> reactionOpt = reactionRepository.findById(reactionId);
        if (!reactionOpt.isPresent()) {
            throw new RuntimeException("Reaction not found");
        }
        return reactionOpt.get();
    }

}
