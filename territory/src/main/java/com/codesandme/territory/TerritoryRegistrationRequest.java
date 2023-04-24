package com.codesandme.territory;

public record TerritoryRegistrationRequest(
		String iso2Code,
		String name,
		String officialName) {

}
