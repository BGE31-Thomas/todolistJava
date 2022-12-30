package com.exemple.projet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import com.exemple.projet.api.exceptions.ResourceNotFoundException;
import com.exemple.projet.api.model.Tache;
import com.exemple.projet.api.model.Utilisateur;
import com.exemple.projet.api.service.TacheService;
import com.exemple.projet.api.service.UserService;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProjetApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private TacheService tacheService;

	private Utilisateur user = new Utilisateur("Thomas", "thomas@mail.com","thomas");

	private Tache tache = new Tache("Description","Titre");
	
	//Test de la méthode d'enregistreemnt d'un utilisateur
	@Test
	void registerUser() {
		
		Utilisateur savedUser = this.userService.createUser(this.user);
		assertEquals(savedUser.getEmail(),user.getEmail());
	}

	//Test de la méthode de connexion
	@Test
	void login() throws ResourceNotFoundException {
		ResponseEntity<Utilisateur> signedInUser = this.userService.getUtilisateur(this.user);
		if (signedInUser.getBody() != null){
			assertEquals(signedInUser.getBody().getEmail(),user.getEmail());
			this.user = signedInUser.getBody();
		}

	}

	//test de la méthode pour la création d'une tâche
	@Test
	void createTache() throws ResourceNotFoundException{
		login();
		
		Integer userId = this.user.id;
		if (userId != 0){
			Tache createdTache = this.tacheService.createTache(userId, tache);
			assertEquals(createdTache.getTitle(),tache.getTitle());
			this.tache = createdTache;
		}
		
	}

	//Test de la méthode de récupération d'une tâche
	@Test
	void getTache() throws ResourceNotFoundException{
		createTache();
		Tache sentTache = this.tacheService.getTacheById(this.tache.getId());
		assertEquals(this.tache.getId(),sentTache.getId());
	}

	//Test de la méthode pour modifier une tâche
	@Test
	void modifyTache() throws ResourceNotFoundException{
		createTache();
		Tache tacheToSend = new Tache("Description","Nouveau titre");
		Tache modifiedTache = this.tacheService.updateTache(this.user.id, this.tache.getId(), tacheToSend);
		assertEquals(tacheToSend.getTitle(), modifiedTache.getTitle());
	}

	//Test de la méthode de modification du statut
	@Test
	void modifyStatus() throws ResourceNotFoundException{
		createTache();
		this.tache.setStatus(false);
		Tache modifiedTache = this.tacheService.updateStatus(this.user.id, this.tache.getId(), this.tache);
		assertEquals(!this.tache.getStatus(), modifiedTache.getStatus());
	}


}
