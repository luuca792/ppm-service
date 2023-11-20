package com.ctu.se.oda.model11.mappers.taskDependency;

import com.ctu.se.oda.model11.entities.TaskDependency;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.taskDependency.CreateTaskDependencyCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateTaskDependencyEntityMapper implements IInfrastructureMapper<CreateTaskDependencyCommandRequest, TaskDependency, CreateTaskDependencyCommandResponse> {

    @Override
    public TaskDependency convert(CreateTaskDependencyCommandRequest source) {
        TaskDependency taskDependency = new TaskDependency(source.getTaskId(), source.getDependentTaskId(), source.getDependencyType());
        return taskDependency;
    }

    @Override
    public CreateTaskDependencyCommandResponse reverse(TaskDependency destination) {
        return null;
    }
}
