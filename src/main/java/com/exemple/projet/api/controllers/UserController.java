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
import com.exemple.projet.api.service.UserService;

@CrossOrigin(origins="*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //Permet de se connecter en renvoyant le bon utilisateur body:utilisateur
    @PostMapping("/login")
    public ResponseEntity  <Utilisateur> getUtilisateur(@RequestBody Utilisateur sendUser) throws ResourceNotFoundException{
        return userService.getUtilisateur(sendUser);
    }

    //Crée un nouvel utilisateur body:utilisateur
    @PostMapping("/users")
    public Utilisateur createUser(@Valid @RequestBody Utilisateur user) {
        return userService.createUser(user);
    }

    
}
