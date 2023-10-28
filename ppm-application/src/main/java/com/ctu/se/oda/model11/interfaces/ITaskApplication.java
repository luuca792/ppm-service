package com.ctu.se.oda.model11.interfaces;


import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;

import java.util.List;
import java.util.UUID;

public interface ITaskApplication {
    CreateTaskCommandResponse createTask(CreateTaskCommandRequest createTaskCommandRequest);

    UpdateTaskCommandResponse updateTask(UpdateTaskCommandRequest updateTaskCommandRequest);

    List<RetrieveTaskQueryResponse> listTask();

    RetrieveTaskQueryResponse detailTask(UUID taskId);

    CreateTaskCommandResponse addSubTaskToTask(CreateTaskCommandRequest createTaskCommandRequest, UUID taskParentId);
    void deleteTask(UUID taskId);
}
