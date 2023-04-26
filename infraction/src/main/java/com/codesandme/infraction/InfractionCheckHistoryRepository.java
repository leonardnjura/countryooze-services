package com.codesandme.infraction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InfractionCheckHistoryRepository 
	extends JpaRepository<InfractionCheckHistory, Integer>{
}
