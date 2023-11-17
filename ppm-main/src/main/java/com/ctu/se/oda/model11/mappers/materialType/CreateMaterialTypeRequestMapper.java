package com.ctu.se.oda.model11.mappers.materialType;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.materialType.CreateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.materialType.CreateMaterialTypeRequest;

@Component
public class CreateMaterialTypeRequestMapper implements IMainMapper<CreateMaterialTypeRequest, CreateMaterialTypeCommandRequest>{

	@Override
	public CreateMaterialTypeCommandRequest convert(CreateMaterialTypeRequest source) {
		return CreateMaterialTypeCommandRequest.builder()
				.materialName(source.getMaterialTypeName())
				.build();
	}
}
