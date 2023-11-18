package com.ctu.se.oda.model11.mappers.material;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.Material;
import com.ctu.se.oda.model11.entities.MaterialType;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.repositories.IMaterialTypeRepository;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UpdateMaterialEntityMapper implements IInfrastructureMapper<UpdateMaterialCommandRequest, Material> {
    
	private IMaterialTypeRepository materialTypeRepository;
	
	@Override
    public Material convert(UpdateMaterialCommandRequest source) {
		Optional<MaterialType> materialType = materialTypeRepository.findById(source.getMaterialType());
		if (!materialType.isPresent() ) {
			throw new IllegalArgumentException(CustomErrorMessage.MATERIAL_TYPE_ID_NOT_FOUND);
		}
    	return Material.builder()
    			.name(source.getMaterialName())
    			.materialType(materialType.get())
    			.build();
    }

}
