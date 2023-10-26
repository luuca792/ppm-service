package com.ctu.se.oda.model11.mappers.material;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.material.UpdateMaterialRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class UpdateMaterialRequestMapper implements IMainMapper<UpdateMaterialRequest, UpdateMaterialCommandRequest> {
    @Override
    public UpdateMaterialCommandRequest convert(UpdateMaterialRequest source) {
        return UpdateMaterialCommandRequest.builder()
                .materialId(source.getMaterialId())
                .materialName(source.getMaterialName())
                .materialType(source.getMaterialType())
                .build();
    }

    @Override
    public UpdateMaterialRequest reverse(UpdateMaterialCommandRequest destination) {
        return UpdateMaterialRequest.builder()
                .materialId(destination.getMaterialId())
                .materialName(destination.getMaterialName())
                .materialType(destination.getMaterialType())
                .build();
    }
}
