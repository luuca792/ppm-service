package com.ctu.se.oda.model11.models.commands.requests.taskDependency;

import com.ctu.se.oda.model11.enums.DependencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class CreateTaskDependencyCommandRequest {
    private UUID taskId;
    private UUID dependentTaskId;
    private DependencyType dependencyType;
}
