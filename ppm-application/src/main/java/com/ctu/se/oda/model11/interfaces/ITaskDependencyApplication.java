package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.taskDependency.CreateTaskDependencyCommandResponse;

public interface ITaskDependencyApplication {
    void createTaskDependency(CreateTaskDependencyCommandRequest request);
}
