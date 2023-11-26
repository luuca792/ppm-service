package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.interfaces.IProjectApplication;
import com.ctu.se.oda.model11.interfaces.ITaskApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.dtos.TaskDTO;
import com.ctu.se.oda.model11.models.project.CreateProjectRequest;
import com.ctu.se.oda.model11.models.project.UpdateProjectRequest;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import com.ctu.se.oda.model11.models.task.TaskScheduleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectApi {

    @Autowired
    private IMainMapper<CreateProjectRequest, CreateProjectCommandRequest> createProjectMapper;

    @Autowired
    private IMainMapper<UpdateProjectRequest, UpdateProjectCommandRequest> updateProjectMapper;

    @Autowired
    private IProjectApplication projectApplication;

    @Autowired
    private ITaskApplication taskApplication;

    @PostMapping()
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequest createProjectRequest) {
    	projectApplication.createProject(createProjectMapper.convert(createProjectRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@RequestBody UpdateProjectRequest updateProjectRequest, @PathVariable String projectId) {
        updateProjectRequest.setProjectId(projectId);
        projectApplication.updateProject(updateProjectMapper.convert(updateProjectRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<RetrieveProjectQueryResponse>> getAllProjects(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) Boolean isTemplate) {
        return new ResponseEntity<>(projectApplication.getAllProjects(userId, isTemplate), HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<RetrieveProjectQueryResponse> getProjectById(@PathVariable UUID projectId) {
        return new ResponseEntity<>(
                projectApplication.getProjectById(projectId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable UUID projectId) {
        projectApplication.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{projectId}/schedule")
    public ResponseEntity<List<TaskScheduleResult>> scheduleProject(@PathVariable String projectId) {
        projectApplication.scheduleProject(UUID.fromString(projectId));
        List<TaskDTO> tasks = taskApplication.getTasksOfProject(UUID.fromString(projectId));
        List<TaskScheduleResult> results = tasks.stream().map(task -> TaskScheduleResult.builder()
                .taskName(task.getTaskName())
                .taskDuration(task.getTaskDuration())
                .taskStartDate(task.getTaskStartAt())
                .taskEndDate(task.getTaskEndAt())
                .build())
                .sorted(Comparator.comparing(TaskScheduleResult::getTaskStartDate))
                .collect(Collectors.toList());
        return new ResponseEntity<List<TaskScheduleResult>>(results, HttpStatus.OK);
    }
}
