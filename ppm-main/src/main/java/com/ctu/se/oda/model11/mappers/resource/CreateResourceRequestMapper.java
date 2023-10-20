package com.ctu.se.oda.model11.mappers.resource;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.resource.CreateResourceCommandRequest;
import com.ctu.se.oda.model11.models.resource.CreateResourceRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class CreateResourceRequestMapper implements IMainMapper<CreateResourceRequest, CreateResourceCommandRequest> {
    @Override
    public CreateResourceCommandRequest convert(CreateResourceRequest source) {
        return CreateResourceCommandRequest.builder()
                .resourceName(source.getResourceName())
                .taskId(UUID.fromString(source.getTaskId()))
                .build();
    }

    @Override
    public CreateResourceRequest reverse(CreateResourceCommandRequest destination) {
        return CreateResourceRequest.builder()
                .resourceName(destination.getResourceName())
                .taskId(destination.getTaskId().toString())
                .build();
    }
}
