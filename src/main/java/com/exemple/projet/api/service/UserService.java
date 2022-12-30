package com.exemple.projet.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exemple.projet.api.exceptions.ResourceNotFoundException;
import com.exemple.projet.api.model.Utilisateur;
import com.exemple.projet.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Utilisateur createUser(Utilisateur user){
        return userRepository.save(user);
    }

    public ResponseEntity<Utilisateur> getUtilisateur(Utilisateur sendUser) throws ResourceNotFoundException {
        Utilisateur user = userRepository.findByEmailAndPassword(sendUser.email, sendUser.password)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
        return ResponseEntity.ok().body(user);
    }

}
