package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.models.commands.requests.subTask.CreateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.subTask.UpdateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.subTask.RetrieveSubTaskQueryResponse;

import java.util.List;
import java.util.UUID;

public interface ISubTaskService {
    List<RetrieveSubTaskQueryResponse> getAllSubTaskOfTask(UUID id);
    RetrieveSubTaskQueryResponse getSubTaskById(UUID id);
    void createSubTask(CreateSubTaskCommandRequest request);
    void updateSubTask(UpdateSubTaskCommandRequest request);
    void deleteSubTaskById(UUID id);
}
