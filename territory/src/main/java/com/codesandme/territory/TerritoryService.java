package com.codesandme.territory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@AllArgsConstructor
public class TerritoryService {
	
	private final TerritoryRepository territoryRepository;
	private final RestTemplate restTemplate;
	
	public Territory registerTerritory(TerritoryRegistrationRequest request) {
		Territory territory = Territory.builder()
				.iso2Code(request.iso2Code())
				.name(request.name())
				.officialName(request.officialName())
				.build();

		// TODO check if iso2 is valid
		// TODO send notification
		
		Optional<Territory> territoryOptional = territoryRepository.findTerritoryByIso2Code(territory.getIso2Code());
		if(territoryOptional.isPresent()) {
			throw new IllegalStateException("!!Ayayai, iso2 taken");
		}

		Territory savedInsert = territoryRepository.saveAndFlush(territory);
		
		InfractionCheckResponse infractionCheckResponse = restTemplate.getForObject(
				"http://INFRACTION/api/v1/infraction-check/{territoryId}",
				InfractionCheckResponse.class, savedInsert.getId());
 
		if(infractionCheckResponse.isInfraction()) {
			throw new IllegalStateException("!!infraction");
		}
		
		log.info("new territory registration {}", savedInsert);
		return savedInsert;
	}
	
	
	public List<Territory> getAllTerritories(){
		Long total = territoryRepository.count();
		Long fetched = total;
		log.info("fetched " + fetched + " of total " + total + "");
		return territoryRepository.findAll();						
	}
	
	
	public Territory getOneTerritory(Integer id) {
		Territory territory = territoryRepository.findById(id)
				.orElseThrow(() ->  new IllegalStateException("!!Ayayai, territory with id " + id + " doesn't exist"));
		log.info("fetched 1 of id::" + id + "");
		return territory;
	}

	@Transactional
	@Modifying
	public Territory updateTerritory(Integer id, String iso2Code, String name, String officialName) {
		Territory territory = territoryRepository.findById(id)
				.orElseThrow(() ->  new IllegalStateException("!!Ayayai, territory with id " + id + " doesn't exist"));
		Territory savedInsert = territory;
		
		if(iso2Code != null && iso2Code.length() > 0 &&
				!Objects.equals(territory.getIso2Code(), iso2Code)) {
			
			//special
			Optional<Territory> territoryOptional = territoryRepository.findTerritoryByIso2Code(iso2Code);
			if(territoryOptional.isPresent()) {
				throw new IllegalStateException("!!Ayayai, iso2 taken");
			}
			territory.setIso2Code(iso2Code);
			savedInsert = territoryRepository.saveAndFlush(territory);		
			log.info("updated territory {}", savedInsert);
			log.info("territory id::" + id + " iso2Code updated 👉 {}", iso2Code);
		}
		
		if(name != null && name.length() > 0 &&
				!Objects.equals(territory.getName(), name)) {
			territory.setName(name);
			savedInsert = territoryRepository.saveAndFlush(territory);		
			log.info("updated territory {}", savedInsert);			
			log.info("territory id::" + id + "name updated 👉 {}", name);
		}
		
		if(officialName != null && officialName.length() > 0 &&
				!Objects.equals(territory.getOfficialName(), officialName)) {
			territory.setOfficialName(officialName);
			savedInsert = territoryRepository.saveAndFlush(territory);		
			log.info("updated territory {}", savedInsert);
			log.info("territory id::" + id + " officialName updated 👉 {}", officialName);
		}
		return savedInsert;
	}
	
	public void deleteTerritory(Integer id) {
		territoryRepository.findById(id)
				.orElseThrow(() ->  new IllegalStateException("!!Ayayai, territory with id " + id + " doesn't exist"));
		territoryRepository.deleteById(id);
		log.info("territory id::" + id + " deleted");
	}

}
