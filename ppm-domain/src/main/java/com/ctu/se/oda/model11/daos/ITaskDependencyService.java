package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.dtos.TaskDependencyDTO;

import java.util.List;
import java.util.UUID;

public interface ITaskDependencyService {

    void addDependency(CreateTaskDependencyCommandRequest createTaskDependencyCommandRequest);

    void deleteDependencyById(UUID dependencyId);

    List<TaskDependencyDTO> getDependentTasks(UUID taskId);
}
