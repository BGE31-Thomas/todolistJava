package com.exemple.projet.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemple.projet.api.model.Tache;


public interface TacheRepository extends JpaRepository<Tache,Integer> {
    List<Tache> findByUtilisateurId(int utilisateurId);
    Optional<Tache> findByIdAndUtilisateurId(int id, int utilisateurId);
}
