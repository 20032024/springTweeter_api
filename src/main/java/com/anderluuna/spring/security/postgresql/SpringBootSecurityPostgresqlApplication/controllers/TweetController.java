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

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Category;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Postre;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Tweet;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.TweetRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.CategoryRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.PostreRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostreRepository postreRepository;

    @GetMapping("/all")
    public Page<Tweet> getTweet(Pageable pageable) {
        return tweetRepository.findAll(pageable);
    }

@PostMapping("/create")
public Tweet createTweet(@Valid @RequestBody Tweet tweetRequest) {

    // Obtener el usuario autenticado
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();
    System.out.println("userid : " + userId);

    // Obtener el usuario autenticado
    User user = getValidUser(userId);
    System.out.println("user");
    System.out.println(user);

    // Verificar si la categoría existe
    Category category = categoryRepository.findById(tweetRequest.getCategoria().getId())
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

    // Crear el tweet y asignar sus campos
    Tweet myTweet = new Tweet();
    myTweet.setTweet(tweetRequest.getTweet());
    myTweet.setIngredientes(tweetRequest.getIngredientes());
    myTweet.setNamePostre(tweetRequest.getNamePostre());
    myTweet.setTipoPostre(tweetRequest.getTipoPostre());
    myTweet.setCategoria(category);  // Asignar la categoría completa al tweet
    myTweet.setPostedBy(user);

    // Guardar el tweet
    tweetRepository.save(myTweet);

    return myTweet;
}


    private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOpt.get();
    }
}
