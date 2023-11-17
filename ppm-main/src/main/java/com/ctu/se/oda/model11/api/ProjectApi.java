package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.interfaces.IProjectApplication;
import com.ctu.se.oda.model11.interfaces.ITaskApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.project.UpdateProjectCommandResponse;
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
    public ResponseEntity<CreateProjectCommandResponse> createProject(@RequestBody CreateProjectRequest createProjectRequest) {
        return new ResponseEntity<>(
                projectApplication.createProject(createProjectMapper.convert(createProjectRequest)),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<UpdateProjectCommandResponse> updateProject(@RequestBody UpdateProjectRequest updateProjectRequest, @PathVariable UUID projectId) {
        updateProjectRequest.setProjectId(projectId);
        return new ResponseEntity<>(
                projectApplication.updateProject(updateProjectMapper.convert(updateProjectRequest)),
                HttpStatus.OK
        );
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

    @PostMapping("/{projectId}/schedule")
    public ResponseEntity<?> scheduleProject(@PathVariable String projectId) {
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
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
