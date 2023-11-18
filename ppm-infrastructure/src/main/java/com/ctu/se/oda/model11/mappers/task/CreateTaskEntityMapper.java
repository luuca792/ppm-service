package com.ctu.se.oda.model11.mappers.task;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.enums.TaskStatus;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.repositories.ITaskRepository;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CreateTaskEntityMapper implements IInfrastructureMapper<CreateTaskCommandRequest, Task>{
    @Autowired
    private ITaskRepository taskRepository;
    @Override
    public Task convert(CreateTaskCommandRequest source) {
        Task task = new Task(
                source.getTaskName(),
                source.getTaskDescription(),
                source.getTaskStartAt(),
                source.getTaskEndAt(),
                source.getTaskDuration(),
                TaskStatus.OPEN,
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
    
}
