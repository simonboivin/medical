package com.atos.medical.model.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    public CommandLineRunner initDatabase() {

    }

}


@Configuration
public class LoadDatabase {

    @Bean
    public CommandLineRunner initDatabase(
            DemoRepository demoRepository,
            PatientRepository patientRepository,
            VilleRepository villeRepository) {
        return args -> {
            if (demoRepository.count() == 0 && patientRepository.count() == 0 && villeRepository.count() == 0) {

                demoRepository.save(new Demo("Lola", "Code00001"));
                demoRepository.save(new Demo("Charles", "Code00002"));

                Ville marseille = Ville.builder()
                        .nom("marseille")
                        .codePostal(13000)
                        .build();

                Arrays.asList(
                        marseille,
                        Ville.builder()
                                .nom("paris")
                                .codePostal(75000)
                                .build()
                ).forEach(villeRepository::save);

                Patient lola = Patient.builder()
                        .nom("Boyer")
                        .prenom("Lola")
                        .telephone("0606060606")
                        .email("lola@boyer.com")
                        .photo("lola.jpg")
                        .build();

                patientRepository.save(lola);
                lola.setVille(marseille);
                patientRepository.save(lola);


            }

        };
    }

}