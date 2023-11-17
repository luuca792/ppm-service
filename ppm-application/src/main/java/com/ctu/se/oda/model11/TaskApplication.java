package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.daos.ITaskDependencyService;
import com.ctu.se.oda.model11.daos.ITaskService;
import com.ctu.se.oda.model11.interfaces.ITaskApplication;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import com.ctu.se.oda.model11.models.dtos.TaskDTO;
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

    @Autowired
    private ITaskDependencyService taskDependencyService;

    @Override
    public CreateTaskCommandResponse createTask(CreateTaskCommandRequest createTaskCommandRequest) {
        return taskService.createTask(createTaskCommandRequest);
    }

    @Override
    public UpdateTaskCommandResponse updateTask(UpdateTaskCommandRequest updateTaskCommandRequest) {
        return taskService.updateTask(updateTaskCommandRequest);
    }

    @Override
    public List<RetrieveTaskQueryResponse> listTask() {
        return taskService.listTask();
    }

    @Override
    public RetrieveTaskQueryResponse detailTask(UUID taskId) {
        return taskService.detailTask(taskId);
    }

    @Override
    public TaskDTO getTaskById(UUID taskId) {
        return taskService.getTaskById(taskId);
    }

    @Override
    public void addMaterialToTask(UUID taskId, UUID materialId, Double amount) {
        taskService.addMaterialToTask(taskId, materialId, amount);
    }

    @Override
    public void deleteTask(UUID taskId) {
        this.taskService.deleteTask(taskId);
    }

    @Override
    public List<TaskDTO> getTasksOfProject(UUID projectId) {
        return this.taskService.getTasksOfProject(projectId);
    }
}
