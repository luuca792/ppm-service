package com.ctu.se.oda.model11.mappers.task;

import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor

public class UpdateTaskEntityMapper implements IInfrastructureMapper<UpdateTaskCommandRequest, Task, UpdateTaskCommandResponse> {
    @Override
    public Task convert(UpdateTaskCommandRequest source) {
        Task task = Task.builder()
                .id(source.getTaskId())
                .name(source.getTaskName())
                .description(source.getTaskDescription())
                .startAt(source.getTaskStartAt())
                .endAt(source.getTaskEndAt())
                .status(source.getTaskStatus())
                .build();
        return task;
    }
    @Override
    public UpdateTaskCommandResponse reverse(Task destination) {
        return UpdateTaskCommandResponse.builder()
                .taskId(destination.getId())
                .taskName(destination.getName())
                .taskDescription(destination.getDescription())
                .taskStartAt(destination.getStartAt())
                .taskEndAt(destination.getEndAt())
                .taskDuration(destination.getDuration())
                .taskStatus(destination.getStatus())
                .projectId(destination.getProjectId())
                .build();
    }
}
