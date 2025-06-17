package com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Postre;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.PostreRepository;
import com.anderluuna.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.security.services.Interfaces.IPostre;

@Service
public class PostreServiceImpl implements IPostre {
    @Autowired
    private PostreRepository postreRepository;

    @Override
    public Postre createPostre(Postre postre) {
        return postreRepository.save(postre);
    }

    @Override
    public Postre getPostreById(Long id) {
        Optional<Postre> postre = postreRepository.findById(id);
        return postre.orElse(null);
    }

    @Override
    public List<Postre> getAllPostres() {
        return postreRepository.findAll();
    }

    @Override
    public Postre updatePostre(Long id, Postre postre) {
        if(postreRepository.existsById(id)) {
            postre.setId(id);
            return postreRepository.save(postre);
        }
        return null;
    }

    @Override
    public void deletePostre(Long id) {
        if(postreRepository.existsById(id)) {
            postreRepository.deleteById(id);
        }
    }
}