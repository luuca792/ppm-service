package com.ctu.se.oda.model11.mappers.task;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.project.UpdateProjectRequest;
import com.ctu.se.oda.model11.models.task.UpdateTaskRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class UpdateTaskRequestMapper implements IMainMapper<UpdateTaskRequest, UpdateTaskCommandRequest> {

    @Override
    public UpdateTaskCommandRequest convert(UpdateTaskRequest source) {
        return UpdateTaskCommandRequest.builder()
                .taskId(source.getTaskId())
                .taskName(source.getTaskName())
                .taskDescription(source.getTaskDescription())
                .taskStartAt(source.getTaskStartAt())
                .taskEndAt(source.getTaskEndAt())
                .projectId(source.getProjectId())
                .build();
    }

    @Override
    public UpdateTaskRequest reverse(UpdateTaskCommandRequest destination) {
        return UpdateTaskRequest.builder()
                .taskId(destination.getTaskId())
                .taskName(destination.getTaskName())
                .taskDescription(destination.getTaskDescription())
                .taskStartAt(destination.getTaskStartAt())
                .taskEndAt(destination.getTaskEndAt())
                .projectId(destination.getProjectId())
                .build();
    }
}
