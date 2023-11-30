package com.ctu.se.oda.model11.mappers.subTask;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.subTask.CreateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.subTask.CreateSubTaskRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@NoArgsConstructor
public class CreateSubTaskRequestMapper implements IMainMapper<CreateSubTaskRequest, CreateSubTaskCommandRequest> {
    @Override
    public CreateSubTaskCommandRequest convert(CreateSubTaskRequest source) {
        return CreateSubTaskCommandRequest.builder()
                .subTaskName(source.getSubTaskName())
                .subTaskDescription(Optional.ofNullable(source.getDescription()).orElse(null))
                .taskParentId(UUID.fromString(source.getTaskParentId()))
                .build();
    }
}
