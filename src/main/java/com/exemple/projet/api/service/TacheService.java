package com.exemple.projet.api.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.projet.api.exceptions.ResourceNotFoundException;
import com.exemple.projet.api.model.Tache;
import com.exemple.projet.repository.TacheRepository;
import com.exemple.projet.repository.UserRepository;

@Service
public class TacheService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TacheRepository tacheRepository;

    public List <Tache> getTachesByUtilisateur(int userId) {
        return tacheRepository.findByUtilisateurId(userId,Sort.by("id").descending());
    }

    public Tache getTacheById(int tacheId) throws ResourceNotFoundException {
        return tacheRepository.findById(tacheId).orElseThrow(() -> new ResourceNotFoundException("tâche non trouvée"));
    }

    public Tache createTache(int userId,Tache tache) throws ResourceNotFoundException {
        return userRepository.findById(userId).map(user -> {
            tache.setUtilisateur(user);
            return tacheRepository.save(tache);
        }).orElseThrow(() -> new ResourceNotFoundException("utilisateur non trouvé"));
    }

    public Tache updateTache(int userId,int tacheId,Tache tacheRequest)
    throws ResourceNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId non trouvé");
        }

        return tacheRepository.findById(tacheId).map(tache -> {
            tache.setTitle(tacheRequest.getTitle());
            tache.setDescription(tacheRequest.getDescription());
            return tacheRepository.save(tache);
        }).orElseThrow(() -> new ResourceNotFoundException("tache.id non trouvée"));
    }

    public Tache updateStatus(int userId,int tacheId,Tache tacheRequest)throws ResourceNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId non trouvé");
        }

        return tacheRepository.findById(tacheId).map(tache -> {
            tache.setStatus(!tacheRequest.getStatus());
            return tacheRepository.save(tache);
        }).orElseThrow(() -> new ResourceNotFoundException("tache.id non trouvée"));
    }

    public void deleteTache(int id){
        tacheRepository.deleteById(id);
    }
    
}    
