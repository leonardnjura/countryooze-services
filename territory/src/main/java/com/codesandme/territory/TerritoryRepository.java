package com.codesandme.territory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TerritoryRepository 
		extends JpaRepository<Territory, Integer> {
	
	/* MY CUSTOM METHODS
	 * */
	@Query("SELECT item FROM Territory item WHERE item.iso2Code = ?1") //optional, JPQL to be more specific
	Optional<Territory> findTerritoryByIso2Code(String iso2Code);

}
 