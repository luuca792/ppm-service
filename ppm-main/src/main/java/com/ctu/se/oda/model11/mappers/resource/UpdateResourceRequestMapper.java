package com.ctu.se.oda.model11.mappers.resource;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.resource.UpdateResourceCommandRequest;
import com.ctu.se.oda.model11.models.resource.UpdateResourceRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class UpdateResourceRequestMapper implements IMainMapper<UpdateResourceRequest, UpdateResourceCommandRequest> {
    @Override
    public UpdateResourceCommandRequest convert(UpdateResourceRequest source) {
        return UpdateResourceCommandRequest.builder()
                .resourceName(source.getResourceName())
                .taskId(UUID.fromString(source.getTaskId()))
                .build();
    }

    @Override
    public UpdateResourceRequest reverse(UpdateResourceCommandRequest destination) {
        return UpdateResourceRequest.builder()
                .resourceId(Long.valueOf(destination.getResourceId().toString()))
                .resourceName(destination.getResourceName())
                .taskId(destination.getTaskId().toString())
                .build();
    }
}
