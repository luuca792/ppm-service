package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.interfaces.IProjectApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.project.CreateProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectApi {
    @Autowired
    private IMainMapper projectMapper;
    @Autowired
    private IProjectApplication projectApplication;

    @PostMapping("")
    public ResponseEntity<CreateProjectCommandResponse> createProject(@RequestBody CreateProjectRequest createProjectRequest) {
        var createProjectCommandRequest = (CreateProjectCommandRequest) this.projectMapper.convert(createProjectRequest);
        return new ResponseEntity<>(this.projectApplication.createProject(createProjectCommandRequest), HttpStatus.CREATED);
    }
}
