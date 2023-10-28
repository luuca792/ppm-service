package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.daos.ITaskService;
import com.ctu.se.oda.model11.interfaces.ITaskApplication;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@NoArgsConstructor
public class TaskApplication implements ITaskApplication {
    @Autowired
    private ITaskService taskService;

    @Override
    public CreateTaskCommandResponse createTask(CreateTaskCommandRequest createTaskCommandRequest) {
        return this.taskService.createTask(createTaskCommandRequest);
    }

    @Override
    public UpdateTaskCommandResponse updateTask(UpdateTaskCommandRequest updateTaskCommandRequest) {
        return this.taskService.updateTask(updateTaskCommandRequest);
    }

    @Override
    public List<RetrieveTaskQueryResponse> listTask() {
        return this.taskService.listTask();
    }

    @Override
    public RetrieveTaskQueryResponse detailTask(UUID taskId) {
        return this.taskService.detailTask(taskId);
    }

    @Override
    public void deleteTask(UUID taskId) {
        this.taskService.deleteTask(taskId);
    }
}
