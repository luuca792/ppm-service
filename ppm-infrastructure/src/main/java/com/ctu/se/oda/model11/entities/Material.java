package com.ctu.se.oda.model11.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "materials")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "material_type_id")
	private MaterialType materialType;

	@JsonIgnore
	@OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
	List<ResourceMaterial> resourceMaterials;
}
