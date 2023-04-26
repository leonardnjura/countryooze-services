package com.codesandme.infraction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("api/v1/infraction-check")
@AllArgsConstructor
public class InfractionCheckHistoryController {
	
	
	private final InfractionCheckHistoryService infractionCheckService;

	@GetMapping(path = "{territoryId}")
	public InfractionCheckResponse isInfraction(
				@PathVariable("territoryId") Integer territoryId) {
		boolean isInfractionTerritory = infractionCheckService.isInfractionTerritory(territoryId);
		return new InfractionCheckResponse(isInfractionTerritory);
	}
}
