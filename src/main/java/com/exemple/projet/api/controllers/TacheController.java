package com.exemple.projet.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.projet.api.exceptions.ResourceNotFoundException;
import com.exemple.projet.api.model.Tache;
import com.exemple.projet.api.service.TacheService;

@CrossOrigin(origins="*")
@RestController
public class TacheController {

    @Autowired
    private TacheService tacheService;

    //Récupère les tâches d'un utilisateur (id de l'utilisateur)
    @GetMapping("/users/{userId}/taches")
    public List <Tache> getTachesByUtilisateur(@PathVariable(value = "userId") int userId) {
        return tacheService.getTachesByUtilisateur(userId);
    }

    //Récupère la tâche ciblée (id de la tâche à récupérer)
    @GetMapping("/taches/{tacheId}")
    public Tache getTacheById(@PathVariable(value = "tacheId") int tacheId) throws ResourceNotFoundException {
        return tacheService.getTacheById(tacheId);
    }


    //Crée une tâche (id de l'utilisateur) body:Tâche à créer
    @PostMapping("/users/{userId}/taches")
    public Tache createTache(@PathVariable(value = "userId") int userId,
        @Valid @RequestBody Tache tache) throws ResourceNotFoundException {
        return tacheService.createTache(userId, tache);
    }

    //Modifie une tâche (id de l'utilisateur,is de la tâche) body: tâche à modifier
    @PutMapping("/users/{userId}/taches/{tacheId}")
    public Tache updateTache(@PathVariable(value = "userId") int userId,
        @PathVariable(value = "tacheId") int tacheId, @Valid @RequestBody Tache tacheRequest)
    throws ResourceNotFoundException {
        return tacheService.updateTache(userId, tacheId, tacheRequest);
    }

    //Modifie le statut d'une tâche (id le l'utilisateur, id de la tâche) body :Tâche à modifier
    @PutMapping("/status/{userId}/{tacheId}")
    public Tache updateStatus(@PathVariable(value = "userId") int userId,
        @PathVariable(value = "tacheId") int tacheId, @Valid @RequestBody Tache tacheRequest)
    throws ResourceNotFoundException {
        return tacheService.updateStatus(userId, tacheId, tacheRequest);
    }

    //Supprime la tâche (id de la tâche à supprimer)
    @DeleteMapping("taches/{id}")
    public void deleteTache(@PathVariable int id){
        tacheService.deleteTache(id);
    }

}
