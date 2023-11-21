package com.ctu.se.oda.model11.mappers.task;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.task.CreateTaskRequest;

import lombok.NoArgsConstructor;

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
                .taskDuration(source.getTaskDuration())
                .projectId(UUID.fromString(source.getProjectId()))
                .build();
    }
}
