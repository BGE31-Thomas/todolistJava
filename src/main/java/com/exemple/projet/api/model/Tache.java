package com.exemple.projet.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tache {

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

    
    public Tache(int id, String description, String title, Boolean status) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.status = status;
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
    
    
    
}
