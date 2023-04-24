package com.codesandme.infraction;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InfractionCheckHistory {
	@Id
	@SequenceGenerator(
			name = "infraction_id_sequence",
			sequenceName = "infraction_id_sequence"
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "infraction_id_sequence"
			)
	private Integer id;
	private Integer territoryId; // foreign key, not iso2
	private Boolean isInfraction;
	private LocalDateTime createdAt;

}
