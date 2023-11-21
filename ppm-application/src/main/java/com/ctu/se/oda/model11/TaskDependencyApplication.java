package com.ctu.se.oda.model11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.daos.ITaskDependencyService;
import com.ctu.se.oda.model11.interfaces.ITaskDependencyApplication;
import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class TaskDependencyApplication implements ITaskDependencyApplication {

    @Autowired
    private ITaskDependencyService taskDependencyService;

    @Override
    public void createTaskDependency(CreateTaskDependencyCommandRequest request) {
        taskDependencyService.addDependency(request);
    }
}
