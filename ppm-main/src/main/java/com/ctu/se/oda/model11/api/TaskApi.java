package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.interfaces.ITaskApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;
import com.ctu.se.oda.model11.models.task.CreateTaskRequest;
import com.ctu.se.oda.model11.models.task.UpdateTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskApi {
    @Autowired
    private IMainMapper<CreateTaskRequest, CreateTaskCommandRequest> createTaskMapper;
    @Autowired
    private IMainMapper<UpdateTaskRequest, UpdateTaskCommandRequest> updateTaskMapper;
    @Autowired
    private ITaskApplication taskApplication;

    public ResponseEntity<CreateTaskCommandResponse> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        CreateTaskCommandRequest createTaskCommandRequest = this.createTaskMapper.convert(createTaskRequest);
        if ((createTaskRequest.getTaskParentId() == null)) {
            return new ResponseEntity<>(
                    this.taskApplication.createTask(createTaskCommandRequest),
                    HttpStatus.CREATED
            );
        } else {
            return new ResponseEntity(
                    this.taskApplication.addSubTaskToTask(this.createTaskMapper.convert(createTaskRequest), createTaskRequest.getTaskParentId()),
                    HttpStatus.CREATED
            );
        }
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest, @PathVariable String taskId) {
        if (updateTaskRequest == null || taskId == null || taskId.isEmpty()) {
            return new ResponseEntity<>("Invalid request data", HttpStatus.BAD_REQUEST);
        }
        try {
            UpdateTaskCommandResponse updatedTask = this.taskApplication.updateTask(
                    this.updateTaskMapper.convert(updateTaskRequest),
                    UUID.fromString(taskId)
            );
            if (updatedTask == null) {
                return new ResponseEntity<>("Task not found or update failed", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid task ID format", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<RetrieveTaskQueryResponse>> listTask() {
        return new ResponseEntity<>(this.taskApplication.listTask(), HttpStatus.OK);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<RetrieveTaskQueryResponse> detailTask(@PathVariable String taskId) {
        return new ResponseEntity<>(this.taskApplication.detailTask(UUID.fromString(taskId)), HttpStatus.OK);
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId) {
        this.taskApplication.deleteTask(UUID.fromString(taskId));
        return ResponseEntity.noContent().build();
    }
}
