package com.ctu.se.oda.model11.mappers.task;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor

public class UpdateTaskEntityMapper implements IInfrastructureMapper<UpdateTaskCommandRequest, Task> {
    @Override
    public Task convert(UpdateTaskCommandRequest source) {
        Task task = new Task(
                source.getTaskId(),
                source.getTaskName(),
                source.getTaskDescription(),
                source.getTaskStartAt(),
                source.getTaskEndAt(),
                source.getTaskDuration(),
                source.getTaskStatus(),
                source.getProjectId()
        );
        if (source.getTaskParentId() != null) {
            task.setTaskParent(source.getTaskParentId());
        }
        return task;
    }

}
