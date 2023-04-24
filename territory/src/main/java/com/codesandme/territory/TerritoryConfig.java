package com.codesandme.territory;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TerritoryConfig {

//	Enable the bean to insert below items for starter data 
//	Preferred POST via endpoint ~/api/v1/territories so infraction check service can be called before db insert
//  In application.yaml..	
//  To persist data     jpa.hibernate.ddl-auto=update	
//  To start afresh     jpa.hibernate.ddl-auto=create-drop	
//	@Bean
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

	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
