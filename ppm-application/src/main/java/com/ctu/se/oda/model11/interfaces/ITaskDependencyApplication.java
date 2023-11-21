package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;

public interface ITaskDependencyApplication {
    void createTaskDependency(CreateTaskDependencyCommandRequest request);
}
