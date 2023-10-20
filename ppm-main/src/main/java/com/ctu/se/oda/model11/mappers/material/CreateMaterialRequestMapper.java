package com.ctu.se.oda.model11.mappers.material;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.material.CreateMaterialRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateMaterialRequestMapper implements IMainMapper<CreateMaterialRequest, CreateMaterialCommandRequest> {
    @Override
    public CreateMaterialCommandRequest convert(CreateMaterialRequest source) {
        return CreateMaterialCommandRequest.builder()
                .materialName(source.getMaterialName())
                .materialType(source.getMaterialType())
                .build();
    }

    @Override
    public CreateMaterialRequest reverse(CreateMaterialCommandRequest destination) {
        return CreateMaterialRequest.builder()
                .materialName(destination.getMaterialName())
                .materialType(destination.getMaterialType())
                .build();
    }
}
