package com.atos.medical.model.configuration;

import com.atos.medical.model.repositories.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    /**
     * Create an administrator account if users table is empty
     */
    @Bean
    public CommandLineRunner initDatabase() {
        UsersRepository usersRepository;
        return args -> {
            if(usersRepository.count() == 0){

            }
        }

    }

}
