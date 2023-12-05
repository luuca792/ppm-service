package com.ctu.se.oda.model11.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ctu.se.oda.model11.interfaces.ITaskDependencyApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.taskDependency.RetrieveTaskDependencyQueryResponse;
import com.ctu.se.oda.model11.models.taskDependency.CreateTaskDependencyRequest;

@RestController
@RequestMapping("/dependencies")
public class TaskDependencyApi {

    @Autowired
    private IMainMapper<CreateTaskDependencyRequest, CreateTaskDependencyCommandRequest> mapper;

    @Autowired
    private ITaskDependencyApplication taskDependencyApplication;

    @GetMapping
    public ResponseEntity<List<RetrieveTaskDependencyQueryResponse>> getAllTaskDependency(@RequestParam (required = false) String taskId) {
        return new ResponseEntity<List<RetrieveTaskDependencyQueryResponse>>(taskDependencyApplication.getAllTaskDependencies(Optional.ofNullable(taskId).map(UUID::fromString).orElse(null)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateTaskDependencyRequest> addDependency(@RequestBody CreateTaskDependencyRequest createTaskDependencyRequest) {
        CreateTaskDependencyCommandRequest request = mapper.convert(createTaskDependencyRequest);
        taskDependencyApplication.createTaskDependency(request);
        return new ResponseEntity<CreateTaskDependencyRequest>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{dependencyId}")
    public void deleteTaskDependencyById(@PathVariable String dependencyId) {
    	taskDependencyApplication.deleteTaskDependency(UUID.fromString(dependencyId));
    }
}
