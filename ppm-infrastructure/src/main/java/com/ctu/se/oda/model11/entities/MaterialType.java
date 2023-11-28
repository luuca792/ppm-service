package com.ctu.se.oda.model11.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "material_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialType {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "materialType", cascade = CascadeType.ALL)
	private List<Material> materials;
}
