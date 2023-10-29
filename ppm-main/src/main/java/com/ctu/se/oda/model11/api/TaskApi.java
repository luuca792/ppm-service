package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.constants.ConstantLibrary;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.entities.Material;
import com.ctu.se.oda.model11.entities.ResourceMaterial;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.interfaces.ITaskApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;
import com.ctu.se.oda.model11.models.task.CreateTaskRequest;
import com.ctu.se.oda.model11.models.task.UpdateTaskRequest;
import com.ctu.se.oda.model11.repositories.IMaterialRepository;
import com.ctu.se.oda.model11.repositories.IResourceMaterialRepository;
import com.ctu.se.oda.model11.repositories.IResourceRepository;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
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
    @Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private IResourceRepository resourceRepository;
    @Autowired
    private IMaterialRepository materialRepository;
    @Autowired
    private IResourceMaterialRepository resourceMaterialRepository;

    @PostMapping()
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        if (Objects.isNull(createTaskRequest.getProjectId())) {
            throw new InternalServerErrorException(ConstantLibrary.MISSING_PARAMS_WARNING);
        }
        CreateTaskCommandRequest createTaskCommandRequest = createTaskMapper.convert(createTaskRequest);
        return new ResponseEntity<>(taskApplication.createTask(createTaskCommandRequest), HttpStatus.CREATED
        );

    }
    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest, @PathVariable String taskId) {
        updateTaskRequest.setTaskId(taskId);
        if (Objects.isNull(updateTaskRequest.getTaskId())) {
            throw new InternalServerErrorException(ConstantLibrary.MISSING_PARAMS_WARNING);
        }
        return new ResponseEntity<>(
            taskApplication.updateTask(updateTaskMapper.convert(updateTaskRequest)), HttpStatus.OK
        );
    }
    @GetMapping()
    public ResponseEntity<List<RetrieveTaskQueryResponse>> listTask() {
        return new ResponseEntity<>(taskApplication.listTask(), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<RetrieveTaskQueryResponse> detailTask(@PathVariable String taskId) {
        return new ResponseEntity<>(taskApplication.detailTask(UUID.fromString(taskId)), HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId) {
        taskApplication.deleteTask(UUID.fromString(taskId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{taskId}/material/{materialId}")
    public ResponseEntity<?> addMaterialToTask(@PathVariable String taskId,
                                               @PathVariable String materialId,
                                               @RequestParam Double amount) {
        taskApplication.addMaterialToTask(UUID.fromString(taskId), UUID.fromString(materialId), amount);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
