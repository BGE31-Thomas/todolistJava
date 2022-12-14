package com.exemple.projet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exemple.projet.api.model.Tache;


public interface TacheRepository extends JpaRepository<Tache,Integer> {
    
}
