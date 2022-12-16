package com.exemple.projet.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.projet.api.exceptions.ResourceNotFoundException;
import com.exemple.projet.api.model.Utilisateur;
import com.exemple.projet.repository.UserRepository;

@CrossOrigin(origins="*")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //Permet de se connecter en renvoyant le bon utilisateur body:utilisateur
    @PostMapping("/login")
    public ResponseEntity  <Utilisateur> getUtilisateur(
        @RequestBody Utilisateur sendUser) throws ResourceNotFoundException {
        Utilisateur user = userRepository.findByEmailAndPassword(sendUser.email, sendUser.password)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
        return ResponseEntity.ok().body(user);
    }

    //Crée un nouvel utilisateur body:utilisateur
    @PostMapping("/users")
    public Utilisateur createUser(@Valid @RequestBody Utilisateur user) {
        return userRepository.save(user);
    }

    
}
