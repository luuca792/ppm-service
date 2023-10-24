package com.ctu.se.oda.model11.mappers.task;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateTaskEntityMapper implements IInfrastructureMapper<CreateTaskCommandRequest, Task, CreateTaskCommandResponse>{

    @Override
    public Task convert(CreateTaskCommandRequest source) {
        return new Task(
                source.getTaskName(),
                source.getTaskDescription(),
                source.getTaskStartAt(),
                source.getTaskEndAt(),
                source.getTaskDuration()
        );
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
                .build();
    }
}
