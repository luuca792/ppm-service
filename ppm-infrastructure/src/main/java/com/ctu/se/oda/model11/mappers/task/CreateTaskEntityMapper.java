package com.ctu.se.oda.model11.mappers.task;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class CreateTaskEntityMapper implements IInfrastructureMapper<CreateTaskCommandRequest, Task, CreateTaskCommandResponse>{
    @Autowired
    private ITaskRepository taskRepository;
    @Override
    public Task convert(CreateTaskCommandRequest source) {
        Task task = new Task(
                source.getTaskName(),
                source.getTaskDescription(),
                source.getTaskStartAt(),
                source.getTaskEndAt(),
                source.getProjectId()
        );

        if (source.getTaskParentId() != null) {
            Task parentTask = taskRepository.findById(source.getTaskParentId()).orElse(null);
            task.setTaskParent(parentTask);
        }

        if (source.getSubtasks() != null && !source.getSubtasks().isEmpty()) {
            task.setSubtasks(source.getSubtasks().stream()
                    .map(this::convert)
                    .collect(Collectors.toList()));
        }

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
                .projectId(destination.getProjectId())
                .build();
    }
}
