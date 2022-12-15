package com.exemple.projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.projet.api.model.Utilisateur;

public interface UserRepository extends JpaRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByNameAndPassword(String name, String password);
}
