package com.ctu.se.oda.model11.mappers.resource;

import com.ctu.se.oda.model11.entities.Resource;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.resource.CreateResourceCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.resource.CreateResourceCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateResourceEntityMapper implements IInfrastructureMapper<CreateResourceCommandRequest, Resource, CreateResourceCommandResponse>{

    @Override
    public Resource convert(CreateResourceCommandRequest source) {
        return new Resource(
                source.getResourceName(),
                source.getTaskId()
        );
    }

    @Override
    public CreateResourceCommandResponse reverse(Resource destination) {
        return new CreateResourceCommandResponse(
                destination.getId(),
                destination.getName(),
                destination.getTaskId()
        );
    }
}
