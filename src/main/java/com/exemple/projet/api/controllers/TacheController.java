package com.exemple.projet.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.projet.api.exceptions.ResourceNotFoundException;
import com.exemple.projet.api.model.Tache;
import com.exemple.projet.repository.TacheRepository;
import com.exemple.projet.repository.UserRepository;

@CrossOrigin(origins="*")
@RestController
public class TacheController {

    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/taches")
    public List <Tache> getTachesByUtilisateur(@PathVariable(value = "postId") int userId) {
        return tacheRepository.findByUtilisateurId(userId);
    }

    @PostMapping("/users/{userId}/taches")
    public Tache createTache(@PathVariable(value = "userId") int userId,
        @Valid @RequestBody Tache tache) throws ResourceNotFoundException {
        return userRepository.findById(userId).map(user -> {
            tache.setUtilisateur(user);
            return tacheRepository.save(tache);
        }).orElseThrow(() -> new ResourceNotFoundException("utilisateur non trouvé"));
    }

    @PutMapping("/users/{userId}/taches/{tacheId}")
    public Tache updateTache(@PathVariable(value = "userId") int userId,
        @PathVariable(value = "tacheId") int tacheId, @Valid @RequestBody Tache tacheRequest)
    throws ResourceNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId non trouvé");
        }

        return tacheRepository.findById(tacheId).map(tache -> {
            tache.setTitle(tacheRequest.getTitle());
            return tacheRepository.save(tache);
        }).orElseThrow(() -> new ResourceNotFoundException("tache.id non trouvée"));
    }

    @DeleteMapping("/users/{userId}/taches/{tacheId}")
    public ResponseEntity <?> deleteTache(@PathVariable(value = "userId") int userId,
        @PathVariable(value = "tacheId") int tacheId) throws ResourceNotFoundException {
        return tacheRepository.findByIdAndUtilisateurId(tacheId, userId).map(tache -> {
            tacheRepository.delete(tache);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
            "Tache non trouvée avec l'id " + tacheId + " et userId " + userId));
    }

    /* @RequestMapping("/taches")
    public List<Tache> getTaches(){
        return (List<Tache>) tacheRepository.findAll(Sort.by("id").descending());
    }
    
    @GetMapping("/tache/{id}")
    public Tache getTache(@PathVariable Integer id){
       return tacheRepository.findById(id).orElse(null);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "tache/{id}")
    public void deleteTache(@PathVariable int id){
        tacheRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/taches")
    public void addTache(@RequestBody Tache tache){
        tacheRepository.save(tache);
    }

    @RequestMapping(method = RequestMethod.PUT,value="/tache/{id}")
    public void updateTache(@RequestBody Tache tache, @PathVariable int id){

        Tache tacheToFind = tacheRepository.findById(id).orElse(null);
        if (tacheToFind != null){
            tacheToFind.setTitle(tache.getTitle());
            tacheToFind.setDescription(tache.getDescription());
            tacheRepository.save(tacheToFind);
        }
    }
    @RequestMapping(method = RequestMethod.PUT,value="/status/{id}")
    public void updateStatus(@RequestBody Tache tache, @PathVariable int id){

        Tache tacheToFind = tacheRepository.findById(id).orElse(null);
        if (tacheToFind != null){
            
            tacheToFind.setStatus(!tache.getStatus());
            tacheRepository.save(tacheToFind);
        }
    } */

}
