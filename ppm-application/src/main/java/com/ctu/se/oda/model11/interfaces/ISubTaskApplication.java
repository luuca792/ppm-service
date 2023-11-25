package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.commands.requests.subTask.CreateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.subTask.UpdateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.subTask.RetrieveSubTaskQueryResponse;

import java.util.List;
import java.util.UUID;

public interface ISubTaskApplication {
    List<RetrieveSubTaskQueryResponse> getAllSubTaskOfTask(UUID taskId);
    RetrieveSubTaskQueryResponse getSubTaskById(UUID id);
    void createSubTask(CreateSubTaskCommandRequest request);
    void updateSubTask(UpdateSubTaskCommandRequest id);
    void deleteSubTaskById(UUID id);
}
