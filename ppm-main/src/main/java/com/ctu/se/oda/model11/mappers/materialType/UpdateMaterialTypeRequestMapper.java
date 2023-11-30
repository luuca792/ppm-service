package com.ctu.se.oda.model11.mappers.materialType;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.materialType.UpdateMaterialTypeCommandRequest;
import com.ctu.se.oda.model11.models.materialType.UpdateMaterialTypeRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateMaterialTypeRequestMapper implements IMainMapper<UpdateMaterialTypeRequest, UpdateMaterialTypeCommandRequest> {
    @Override
    public UpdateMaterialTypeCommandRequest convert(UpdateMaterialTypeRequest source) {
        return UpdateMaterialTypeCommandRequest.builder()
                .materialTypeId(UUID.fromString(source.getMaterialTypeId()))
                .materialTypeName(source.getMaterialTypeName())
                .build();
    }
}
