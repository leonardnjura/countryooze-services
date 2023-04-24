package com.codesandme.territory;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TerritoryConfig {

//	Enable bean to insert below items for starter data or insert via endpoint ~/api/v1/territories
//  In application.yaml..	
//  To persist data     jpa.hibernate.ddl-auto=update	
//  To start afresh     jpa.hibernate.ddl-auto=create-drop	
	@Bean
	CommandLineRunner commandLineRunner(TerritoryRepository repository) {
		return args -> {

			Territory ke = new Territory(
					"ke", 
					"Kenya", 
					"Republic of Kenya");

			Territory uk = new Territory(
					"uk", 
					"United Kingdom",
					"United Kingdom of Great Britain");
			
			Territory tj = new Territory(
					"tj", 
					"Tajikistan",
					"Republic of Tajikistan");
			
			repository.saveAll(
					List.of(ke, uk, tj)
					);

		};
	}

}
