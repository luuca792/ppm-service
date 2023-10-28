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

import java.util.stream.Collectors;
import java.util.Objects;

@Component
@NoArgsConstructor

public class UpdateTaskEntityMapper implements IInfrastructureMapper<UpdateTaskCommandRequest, Task, UpdateTaskCommandResponse> {
    @Override
    public Task convert(UpdateTaskCommandRequest source) {
        Task task = new Task(
                source.getTaskId(),
                source.getTaskName(),
                source.getTaskDescription(),
                source.getTaskStartAt(),
                source.getTaskEndAt(),
                source.getProjectId()
        );

        if (source.getTaskParentId() != null) {
            task.setTaskParent(source.getTaskParentId());
        }

        return task;
    }
    @Override
    public UpdateTaskCommandResponse reverse(Task destination) {
        UpdateTaskCommandResponse.UpdateTaskCommandResponseBuilder responseBuilder = UpdateTaskCommandResponse.builder()
                .taskId(destination.getId())
                .taskName(destination.getName())
                .taskDescription(destination.getDescription())
                .taskStartAt(destination.getStartAt())
                .taskEndAt(destination.getEndAt())
                .projectId(destination.getProjectId())
                .taskParentId(destination.getTaskParent() != null ? destination.getTaskParent().getId() : null);
        if (destination.getSubtasks() != null) {
            responseBuilder.subtasks(destination.getSubtasks().stream().map(this::reverse).collect(Collectors.toList()));
        }

        return responseBuilder.build();
    }
}
