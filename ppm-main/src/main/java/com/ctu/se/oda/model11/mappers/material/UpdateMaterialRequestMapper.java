package com.ctu.se.oda.model11.mappers.material;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.material.UpdateMaterialRequest;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UpdateMaterialRequestMapper implements IMainMapper<UpdateMaterialRequest, UpdateMaterialCommandRequest> {
	
    @Override
    public UpdateMaterialCommandRequest convert(UpdateMaterialRequest source) {
        return UpdateMaterialCommandRequest.builder()
                .materialId(UUID.fromString(source.getMaterialId()))
                .materialName(source.getMaterialName())
                .materialType(Optional.ofNullable(source.getMaterialTypeName()).map(UUID::fromString).orElse(null))
                .build();
    }
}
