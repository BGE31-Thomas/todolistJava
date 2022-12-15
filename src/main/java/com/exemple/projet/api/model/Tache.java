package com.exemple.projet.api.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tache extends Utilisateur{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private String description;

    public String title;

    public Boolean status;


    @ManyToOne()
    @JsonIgnore
    public Utilisateur utilisateur;

    public Tache(int id,String description,String title,Boolean status,Utilisateur utilisateur){
        this.id = id;
        this.description = description;
        this.title = title;
        this.status = status;
        this.utilisateur = utilisateur;
    }

    public Tache() {
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    
    
}
