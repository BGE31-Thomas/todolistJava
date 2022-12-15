package com.exemple.projet.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Utilisateur implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "utilisateur_id")
    public int id;
    public String name;
    public String email;
    public String password;


    @OneToMany(mappedBy="utilisateur",cascade = {
        CascadeType.ALL
    })
    @JsonIgnore
    public List<Tache> taches;

    public Utilisateur(int id, String name, String email, String password, List<Tache> taches) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.taches = taches;
    }
    public Utilisateur() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Tache> getTaches() {
        return taches;
    }
    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}
