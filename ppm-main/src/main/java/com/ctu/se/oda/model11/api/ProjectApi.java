package com.ctu.se.oda.model11.api;

import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RestController;

import com.ctu.se.oda.model11.interfaces.IProjectApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.project.CreateProjectRequest;
import com.ctu.se.oda.model11.models.project.UpdateProjectRequest;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;

@RestController
@RequestMapping("/projects")
public class ProjectApi {

    @Autowired
    private IMainMapper<CreateProjectRequest, CreateProjectCommandRequest> createProjectMapper;

    @Autowired
    private IProjectApplication projectApplication;

    @PostMapping()
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequest createProjectRequest) {
    	projectApplication.createProject(createProjectMapper.convert(createProjectRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@RequestBody UpdateProjectRequest updateProjectRequest, @PathVariable UUID projectId) {
        updateProjectRequest.setProjectId(projectId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<RetrieveProjectQueryResponse>> listProject() {
        return new ResponseEntity<>(
            projectApplication.listProject(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<RetrieveProjectQueryResponse> detailProject(@PathVariable UUID projectId) {
        return new ResponseEntity<>(
                projectApplication.detailProject(projectId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable UUID projectId) {
        projectApplication.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }
}
