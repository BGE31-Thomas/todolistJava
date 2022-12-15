package com.exemple.projet.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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


    @GetMapping("/users")
    public List <Utilisateur> getUtilisateurs() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity  <Utilisateur> getUtilisateurById(
        @RequestBody Utilisateur sendUser) throws ResourceNotFoundException {
        Utilisateur user = userRepository.findByNameAndPassword(sendUser.name, sendUser.password)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public Utilisateur createUser(@Valid @RequestBody Utilisateur user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity < Utilisateur > updateUser(
        @PathVariable(value = "id") int userId,
        @Valid @RequestBody Utilisateur userDetails) throws ResourceNotFoundException {
        Utilisateur user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé :: " + userId));
        user.setName(userDetails.getName());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        final Utilisateur updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map <String, Boolean> deleteUser(
        @PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        Utilisateur user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé :: " + userId));

        userRepository.delete(user);
        Map <String, Boolean> response = new HashMap < > ();
        response.put("supprimé", Boolean.TRUE);
        return response;
    }
}
