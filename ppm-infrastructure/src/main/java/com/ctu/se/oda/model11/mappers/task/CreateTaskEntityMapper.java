package com.ctu.se.oda.model11.mappers.task;

import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.enums.TaskStatus;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateTaskEntityMapper implements IInfrastructureMapper<CreateTaskCommandRequest, Task, CreateTaskCommandResponse>{
    @Autowired
    private ITaskRepository taskRepository;
    @Override
    public Task convert(CreateTaskCommandRequest source) {
        Task task = Task.builder()
                .name(source.getTaskName())
                .description(source.getTaskDescription())
                .duration(source.getTaskDuration())
                .projectId(source.getProjectId())
                .status(TaskStatus.OPEN)
                .build();
        return task;
    }

    @Override
    public CreateTaskCommandResponse reverse(Task destination) {
        return CreateTaskCommandResponse.builder()
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
