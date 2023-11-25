package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.interfaces.ISubTaskApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.subTask.CreateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.subTask.UpdateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.subTask.RetrieveSubTaskQueryResponse;
import com.ctu.se.oda.model11.models.subTask.CreateSubTaskRequest;
import com.ctu.se.oda.model11.models.subTask.UpdateSubTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subtasks")
public class SubTaskApi {

    @Autowired
    private IMainMapper<CreateSubTaskRequest, CreateSubTaskCommandRequest> createSubTaskMapper;

    @Autowired
    private IMainMapper<UpdateSubTaskRequest, UpdateSubTaskCommandRequest> updateSubTaskMapper;

    @Autowired
    private ISubTaskApplication subTaskApplication;

    @GetMapping("/{subTaskId}")
    public ResponseEntity<RetrieveSubTaskQueryResponse> getSubTaskById(@PathVariable String subTaskId) {
        return new ResponseEntity<RetrieveSubTaskQueryResponse>(subTaskApplication.getSubTaskById(UUID.fromString(subTaskId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSubtask(@RequestBody CreateSubTaskRequest request) {
        subTaskApplication.createSubTask(createSubTaskMapper.convert(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{subTaskId}")
    public ResponseEntity<?> updateSubTask(@PathVariable String subTaskId, @RequestBody UpdateSubTaskRequest request) {
        request.setSubTaskId(subTaskId);
        subTaskApplication.updateSubTask(updateSubTaskMapper.convert(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{subTaskId}")
    public ResponseEntity<?> deleteSubTaskById(@PathVariable String subTaskId) {
        subTaskApplication.deleteSubTaskById(UUID.fromString(subTaskId));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
