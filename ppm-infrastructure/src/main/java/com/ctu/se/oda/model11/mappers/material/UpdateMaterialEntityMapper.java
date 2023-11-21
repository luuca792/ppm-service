package com.ctu.se.oda.model11.mappers.material;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.Material;
import com.ctu.se.oda.model11.entities.MaterialType;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.repositories.IMaterialTypeRepository;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UpdateMaterialEntityMapper implements IInfrastructureMapper<UpdateMaterialCommandRequest, Material> {
	@Autowired
	private IMaterialTypeRepository materialTypeRepository;

	@Override
	public Material convert(UpdateMaterialCommandRequest source) {
		Optional<MaterialType> materialType = Optional.ofNullable(source.getMaterialType()).map(materialTypeRepository::findById).orElse(null);
		return Material.builder()
				.id(source.getMaterialId())
				.name(source.getMaterialName())
				.materialType(Optional.ofNullable(materialType).map(Optional::get).orElse(null))
				.build();
	}

}
