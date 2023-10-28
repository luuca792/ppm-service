package com.ctu.se.oda.model11.mappers.task;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.task.CreateTaskRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@NoArgsConstructor
public class CreateTaskRequestMapper implements IMainMapper<CreateTaskRequest, CreateTaskCommandRequest> {

    @Override
    public CreateTaskCommandRequest convert(CreateTaskRequest source) {
        return CreateTaskCommandRequest.builder()
                .taskName(source.getTaskName())
                .taskDescription(source.getTaskDescription())
                .taskStartAt(source.getTaskStartAt())
                .taskEndAt(source.getTaskEndAt())
                .projectId(UUID.fromString(source.getProjectId()))
                .taskParentId(Optional.ofNullable(source.getTaskParentId()).map(UUID::fromString).orElse(null))
                .build();
    }

    @Override
    public CreateTaskRequest reverse(CreateTaskCommandRequest destination) {
        return CreateTaskRequest.builder()
                .taskName(destination.getTaskName())
                .taskDescription(destination.getTaskDescription())
                .taskStartAt(destination.getTaskStartAt())
                .taskEndAt(destination.getTaskEndAt())
                .projectId(destination.getProjectId())
                .build();
    }
}
