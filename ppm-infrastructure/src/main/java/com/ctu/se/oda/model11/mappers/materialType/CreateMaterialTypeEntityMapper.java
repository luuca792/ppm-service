package com.ctu.se.oda.model11.mappers.materialType;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.MaterialType;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.materialType.CreateMaterialTypeCommandRequest;

@Component
public class CreateMaterialTypeEntityMapper implements IInfrastructureMapper<CreateMaterialTypeCommandRequest, MaterialType>{

	@Override
	public MaterialType convert(CreateMaterialTypeCommandRequest source) {
		return MaterialType.builder()
				.name(source.getMaterialTypeName())
				.build();
	}
}
