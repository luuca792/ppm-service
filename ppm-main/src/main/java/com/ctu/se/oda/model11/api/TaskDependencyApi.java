package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.interfaces.ITaskDependencyApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.taskDependency.CreateTaskDependencyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dependencies")
public class TaskDependencyApi {

    @Autowired
    private IMainMapper<CreateTaskDependencyRequest, CreateTaskDependencyCommandRequest> mapper;

    @Autowired
    private ITaskDependencyApplication taskDependencyApplication;

    @PostMapping
    public ResponseEntity<?> addDependency(@RequestBody CreateTaskDependencyRequest createTaskDependencyRequest) {
        CreateTaskDependencyCommandRequest request = mapper.convert(createTaskDependencyRequest);
        taskDependencyApplication.createTaskDependency(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
