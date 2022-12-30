package com.exemple.projet.api.configurations;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.exemple.projet.repository.UserRepository;

@Profile("test")
@Configuration
public class UserRepositoryTestConfiguration {
    @Bean
    @Primary
    public UserRepository productRepository() {
        return Mockito.mock(UserRepository.class);
    }
}
