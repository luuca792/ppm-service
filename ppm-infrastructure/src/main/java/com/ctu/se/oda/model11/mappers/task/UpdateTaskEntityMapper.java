package com.ctu.se.oda.model11.mappers.task;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.UpdateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor

public class UpdateTaskEntityMapper implements IInfrastructureMapper<UpdateTaskCommandRequest, Task, UpdateTaskCommandResponse> {
    @Override
    public Task convert(UpdateTaskCommandRequest source) {
        return new Task(
                source.getTaskId(),
                source.getTaskName(),
                source.getTaskDescription(),
                source.getTaskStartAt(),
                source.getTaskEndAt(),
                source.getProjectId()
        );
    }
    @Override
    public UpdateTaskCommandResponse reverse(Task destination) {
        return UpdateTaskCommandResponse.builder()
                .taskId(destination.getId())
                .taskName(destination.getName())
                .taskDescription(destination.getDescription())
                .taskStartAt(destination.getStartAt())
                .taskEndAt(destination.getEndAt())
                .projectId(destination.getProjectId())
                .build();
    }
}
