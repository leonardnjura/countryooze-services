package com.codesandme.territory;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;



@RestController
@RequestMapping("api/v1/territories")
@AllArgsConstructor
public class TerritoryController {
	
	private final TerritoryService territoryService;
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Territory registerTerritory(
			@RequestBody TerritoryRegistrationRequest territoryRegistrationRequest) {
		 return territoryService.registerTerritory(territoryRegistrationRequest);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Territory> getAllTerritories(){
		return territoryService.getAllTerritories();
	}
	
	@GetMapping(path = "{id}")
	@ResponseStatus(HttpStatus.OK)
	public Territory getOneTerritory(
			@PathVariable("id") Integer id){
		return territoryService.getOneTerritory(id);
	}
	
	@PutMapping(path = "{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Territory updateTerritory(
			@PathVariable("id") Integer id,
			@RequestParam(required = false) String iso2Code,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String officialName) {
		return territoryService.updateTerritory(id, iso2Code, name, officialName);
	}
	
	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStudent(
			@PathVariable("id") Integer id) {
		territoryService.deleteTerritory(id);
	}
}
