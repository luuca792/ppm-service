package com.ctu.se.oda.model11.mappers.material;

import com.ctu.se.oda.model11.entities.Material;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.material.CreateMaterialCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.material.CreateMaterialCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateMaterialEntityMapper implements IInfrastructureMapper<CreateMaterialCommandRequest, Material, CreateMaterialCommandResponse>{

    @Override
    public Material convert(CreateMaterialCommandRequest source) {
        return new Material(
                source.getMaterialName(),
                source.getMaterialTypeName()
        );
    }

    @Override
    public CreateMaterialCommandResponse reverse(Material destination) {
        return new CreateMaterialCommandResponse(
                destination.getId(),
                destination.getName(),
                destination.getMaterialTypeName()
        );
    }
}
