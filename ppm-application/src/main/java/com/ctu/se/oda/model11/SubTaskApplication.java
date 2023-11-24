package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.daos.ISubTaskService;
import com.ctu.se.oda.model11.interfaces.ISubTaskApplication;
import com.ctu.se.oda.model11.models.commands.requests.subTask.CreateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.subTask.UpdateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.subTask.RetrieveSubTaskQueryResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@NoArgsConstructor
public class SubTaskApplication implements ISubTaskApplication {

    @Autowired
    private ISubTaskService subTaskService;

    @Override
    public List<RetrieveSubTaskQueryResponse> getAllSubTaskOfTask(UUID taskId) {
        return subTaskService.getAllSubTaskOfTask(taskId);
    }

    @Override
    public RetrieveSubTaskQueryResponse getSubTaskById(UUID id) {
        return subTaskService.getSubTaskById(id);
    }

    @Override
    public void createSubTask(CreateSubTaskCommandRequest request) {
        subTaskService.createSubTask(request);
    }

    @Override
    public void updateSubTask(UpdateSubTaskCommandRequest request) {
        subTaskService.updateSubTask(request);
    }

    @Override
    public void deleteSubTaskById(UUID id) {
        subTaskService.deleteSubTaskById(id);
    }
}
