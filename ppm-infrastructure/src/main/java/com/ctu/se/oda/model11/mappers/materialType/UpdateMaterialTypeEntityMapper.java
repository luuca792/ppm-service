package com.ctu.se.oda.model11.mappers.materialType;

import com.ctu.se.oda.model11.entities.MaterialType;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.materialType.UpdateMaterialTypeCommandRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UpdateMaterialTypeEntityMapper implements IInfrastructureMapper<UpdateMaterialTypeCommandRequest, MaterialType> {

    @Override
    public MaterialType convert(UpdateMaterialTypeCommandRequest source) {
        return MaterialType.builder()
                .id(source.getMaterialTypeId())
                .name(source.getMaterialTypeName())
                .build();
    }
}
