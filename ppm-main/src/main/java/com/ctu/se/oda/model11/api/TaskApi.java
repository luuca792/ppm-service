package com.ctu.se.oda.model11.api;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.ctu.se.oda.model11.interfaces.ISubTaskApplication;
import com.ctu.se.oda.model11.models.queries.responses.subTask.RetrieveSubTaskQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctu.se.oda.model11.constants.ConstantLibrary;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.interfaces.ITaskApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;
import com.ctu.se.oda.model11.models.task.CreateTaskRequest;
import com.ctu.se.oda.model11.models.task.UpdateTaskRequest;

@RestController
@RequestMapping("/tasks")
public class TaskApi {

    @Autowired
    private IMainMapper<CreateTaskRequest, CreateTaskCommandRequest> createTaskMapper;

    @Autowired
    private IMainMapper<UpdateTaskRequest, UpdateTaskCommandRequest> updateTaskMapper;

    @Autowired
    private ITaskApplication taskApplication;

    @Autowired
    private ISubTaskApplication subTaskApplication;

    @PostMapping()
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        if (Objects.isNull(createTaskRequest.getProjectId())) {
            throw new InternalServerErrorException(ConstantLibrary.MISSING_PARAMS_WARNING);
        }
        CreateTaskCommandRequest createTaskCommandRequest = createTaskMapper.convert(createTaskRequest);
        taskApplication.createTask(createTaskCommandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest, @PathVariable String taskId) {
        updateTaskRequest.setTaskId(taskId);
        if (Objects.isNull(updateTaskRequest.getTaskId())) {
            throw new InternalServerErrorException(ConstantLibrary.MISSING_PARAMS_WARNING);
        }
        taskApplication.updateTask(updateTaskMapper.convert(updateTaskRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<RetrieveTaskQueryResponse>> getAllTasks(@RequestParam (required = false) String projectId) {
        return new ResponseEntity<>(taskApplication.listTask(projectId), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<RetrieveTaskQueryResponse> detailTask(@PathVariable String taskId) {
        return new ResponseEntity<>(taskApplication.detailTask(UUID.fromString(taskId)), HttpStatus.OK);
    }

    @GetMapping("/{taskId}/subtasks")
    public ResponseEntity<List<RetrieveSubTaskQueryResponse>> getAllSubTaskOfTask(@PathVariable String taskId) {
        return new ResponseEntity<List<RetrieveSubTaskQueryResponse>>(subTaskApplication.getAllSubTaskOfTask(UUID.fromString(taskId)), HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId) {
        taskApplication.deleteTask(UUID.fromString(taskId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{taskId}/material/{materialId}")
    public ResponseEntity<?> addMaterialToTask(@PathVariable String taskId,
    		@PathVariable String materialId, @RequestParam Double amount) {
        taskApplication.addMaterialToTask(UUID.fromString(taskId), UUID.fromString(materialId), amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}/material/{materialId}")
    public ResponseEntity<?> deleteMaterialFromTask(@PathVariable String taskId,
                                               @PathVariable String materialId) {
        taskApplication.deleteMaterialFromTask(UUID.fromString(taskId), UUID.fromString(materialId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
