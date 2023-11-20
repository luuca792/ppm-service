package com.ctu.se.oda.model11.mappers.taskDependency;

import com.ctu.se.oda.model11.enums.DependencyType;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.taskDependency.CreateTaskDependencyRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class CreateTaskDependencyRequestMapper implements IMainMapper<CreateTaskDependencyRequest, CreateTaskDependencyCommandRequest> {
    @Override
    public CreateTaskDependencyCommandRequest convert(CreateTaskDependencyRequest source) {
        return CreateTaskDependencyCommandRequest.builder()
                .taskId(UUID.fromString(source.getTaskId()))
                .dependentTaskId(UUID.fromString(source.getDependentTaskId()))
                .dependencyType(DependencyType.valueOf(source.getDependencyType()))
                .build();
    }

    @Override
    public CreateTaskDependencyRequest reverse(CreateTaskDependencyCommandRequest destination) {
        return null;
    }
}
