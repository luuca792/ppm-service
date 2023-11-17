package com.ctu.se.oda.model11.daos;


import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import com.ctu.se.oda.model11.models.dtos.TaskDTO;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface ITaskService {
    CreateTaskCommandResponse createTask(@Valid CreateTaskCommandRequest createTaskCommandRequest);

    UpdateTaskCommandResponse updateTask(@Valid UpdateTaskCommandRequest updateTaskCommandRequest);

    List<RetrieveTaskQueryResponse> listTask();

    RetrieveTaskQueryResponse detailTask(UUID taskId);

    TaskDTO getTaskById(UUID taskId);

    void addMaterialToTask(UUID taskId, UUID materialId, Double amount);

    void deleteTask(UUID taskId);

    List<TaskDTO> getTasksOfProject(UUID projectId);
}
