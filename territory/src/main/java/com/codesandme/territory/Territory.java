package com.codesandme.territory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Territory")
@Table(uniqueConstraints = {
			@UniqueConstraint(name = "territory_iso2code_unique", columnNames = "iso2Code")	
		})
public class Territory {

	public Territory(String iso2Code, String name, String officialName) {
		this.iso2Code = iso2Code;
		this.name = name;
		this.officialName = officialName;
	}

	@Id
	@SequenceGenerator(
			name = "territory_id_sequence", 
			sequenceName = "territory_id_sequence", 
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE, 
			generator = "territory_id_sequence")
	@Column(updatable = false)
	private Integer id;

	@Column(nullable = false)
	private String iso2Code;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String officialName;

}
