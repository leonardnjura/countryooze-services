package com.codesandme.infraction;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@AllArgsConstructor
public class InfractionCheckHistoryService {
	
	private final InfractionCheckHistoryRepository infractionCheckHistoryRepository;
	
	
	public boolean isInfractionTerritory(Integer territoryId) {
		
		boolean isInfraction = false; // TODO infringement logic
		
		log.info("infraction check for territory id::{}", territoryId);
		
		infractionCheckHistoryRepository.save(
				InfractionCheckHistory.builder()
				.territoryId(territoryId)
				.isInfraction(isInfraction)
				.createdAt(LocalDateTime.now())
				.build()
				);
		
		
		return isInfraction;
	}

}
