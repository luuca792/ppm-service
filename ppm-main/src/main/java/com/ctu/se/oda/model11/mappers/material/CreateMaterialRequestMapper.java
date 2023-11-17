package com.ctu.se.oda.model11.mappers.material;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.material.CreateMaterialRequest;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CreateMaterialRequestMapper implements IMainMapper<CreateMaterialRequest, CreateMaterialCommandRequest> {
    @Override
    public CreateMaterialCommandRequest convert(CreateMaterialRequest source) {
        return CreateMaterialCommandRequest.builder()
                .materialName(source.getMaterialName())
                .materialType(UUID.fromString(source.getMaterialTypeName()))
                .build();
    }
}
