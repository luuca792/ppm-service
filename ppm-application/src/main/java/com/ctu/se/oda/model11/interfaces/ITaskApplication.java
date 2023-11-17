package com.ctu.se.oda.model11.interfaces;


import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;

public interface ITaskApplication {
    void createTask(CreateTaskCommandRequest createTaskCommandRequest);

    void updateTask(UpdateTaskCommandRequest updateTaskCommandRequest);

    List<RetrieveTaskQueryResponse> listTask();

    RetrieveTaskQueryResponse detailTask(UUID taskId);

    void addMaterialToTask(UUID taskId, UUID materialId, Double amount);

    void deleteTask(UUID taskId);
}
