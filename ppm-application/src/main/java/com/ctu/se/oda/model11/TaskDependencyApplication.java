package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.daos.ITaskDependencyService;
import com.ctu.se.oda.model11.interfaces.ITaskDependencyApplication;
import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.taskDependency.CreateTaskDependencyCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
