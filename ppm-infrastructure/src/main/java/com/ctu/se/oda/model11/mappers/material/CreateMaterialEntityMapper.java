package com.ctu.se.oda.model11.mappers.material;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.Material;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CreateMaterialEntityMapper implements IInfrastructureMapper<CreateMaterialCommandRequest, Material>{

    @Override
    public Material convert(CreateMaterialCommandRequest source) {
    	return Material.builder()
    			.name(source.getMaterialName())
    			.build();
    }

}
