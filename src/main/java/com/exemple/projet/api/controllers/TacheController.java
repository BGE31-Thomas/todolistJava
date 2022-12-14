package com.exemple.projet.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.projet.api.model.Tache;
import com.exemple.projet.repository.TacheRepository;

@CrossOrigin(origins="*")
@RestController
public class TacheController {

    @Autowired
    private TacheRepository tacheRepository;

    @RequestMapping("/taches")
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
    }

}
