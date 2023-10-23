package com.ctu.se.oda.model11.mappers.material;

import com.ctu.se.oda.model11.entities.Material;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.UpdateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.material.UpdateMaterialCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UpdateMaterialEntityMapper implements IInfrastructureMapper<UpdateMaterialCommandRequest, Material, UpdateMaterialCommandResponse> {
    @Override
    public Material convert(UpdateMaterialCommandRequest source) {
        return new Material(
                source.getMaterialId(),
                source.getMaterialName(),
                source.getMaterialType()
        );
    }

    @Override
    public UpdateMaterialCommandResponse reverse(Material destination) {
        return UpdateMaterialCommandResponse.builder()
                .materialId(destination.getId())
                .materialName(destination.getName())
                .materialType(destination.getType())
                .build();
    }
}
