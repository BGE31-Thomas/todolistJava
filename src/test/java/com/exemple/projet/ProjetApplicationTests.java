package com.exemple.projet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.exemple.projet.api.model.Tache;
import com.exemple.projet.api.model.Utilisateur;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProjetApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private Utilisateur user = new Utilisateur("Thomas", "thomas@mail.com","thomas");
	
	@Test
	void registerUser() {
		
		ResponseEntity<Utilisateur> savedUser = this.restTemplate.postForEntity("/users", user, Utilisateur.class);
		assertEquals(savedUser.getBody().getEmail(),user.getEmail());
	}

	@Test
	void login() {
		
		ResponseEntity<Utilisateur> savedUser = this.restTemplate.postForEntity("/login", user, Utilisateur.class);
		assertEquals(savedUser.getBody().getEmail(),user.getEmail());
		this.user = savedUser.getBody();
	}

	@Test
	void createdTache(){
		login();
		Tache tache = new Tache("Titre","Description");
		Integer userId = this.user.id;
		if (userId != null){
			ResponseEntity<Tache> createdTache = this.restTemplate.postForEntity("/users/{userId}/taches",tache, Tache.class,userId);
			assertEquals(createdTache.getBody().getTitle(),tache.getTitle());
		}
		
	}


}
