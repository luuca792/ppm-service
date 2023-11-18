package com.ctu.se.oda.model11.mappers.material;

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
                .materialId(source.getMaterialId())
                .materialName(source.getMaterialName())
                .materialType(source.getMaterialId())
                .build();
    }
}
