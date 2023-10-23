package com.ctu.se.oda.model11.mappers.resource;

import com.ctu.se.oda.model11.entities.Resource;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.resource.UpdateResourceCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.resource.UpdateResourceCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UpdateResourceEntityMapper implements IInfrastructureMapper<UpdateResourceCommandRequest, Resource, UpdateResourceCommandResponse> {
    @Override
    public Resource convert(UpdateResourceCommandRequest source) {
        return new Resource(
                source.getResourceId(),
                source.getResourceName(),
                source.getTaskId()
        );
    }

    @Override
    public UpdateResourceCommandResponse reverse(Resource destination) {
        return UpdateResourceCommandResponse.builder()
                .resourceId(destination.getId())
                .resourceName(destination.getName())
                .taskId(destination.getTaskId())
                .build();
    }
}
