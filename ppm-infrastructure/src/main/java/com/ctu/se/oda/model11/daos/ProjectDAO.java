package com.ctu.se.oda.model11.daos;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import com.ctu.se.oda.model11.repositories.IProjectRepository;
import com.ctu.se.oda.model11.repositories.ITaskRepository;

import jakarta.validation.Valid;

@Service
@Validated
public class ProjectDAO implements IProjectService {
    @Autowired
    private IProjectRepository projectRepository;@Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private IInfrastructureMapper<CreateProjectCommandRequest, Project> createProjectEntityMapper;
    @Autowired
    private IInfrastructureMapper<UpdateProjectCommandRequest, Project> updateProjectEntityMapper;

    @Override
    public void createProject(@Valid CreateProjectCommandRequest createProjectCommandRequest) {
    	createProjectEntityMapper.convert(createProjectCommandRequest);
    }
    @Override
    public void updateProject(@Valid UpdateProjectCommandRequest updateProjectCommandRequest) {
    	updateProjectEntityMapper.convert(updateProjectCommandRequest);
    }
    @Override
    public List<RetrieveProjectQueryResponse> listProject() {
        return projectRepository.findAll().stream()
                .map(project -> RetrieveProjectQueryResponse.builder().projectId(project.getId()).projectName(project.getName()).projectStartAt(project.getStartAt()).projectEndAt(project.getEndAt()).projectDuration(project.getDuration()).projectCreatorId(project.getCreatorId()).build())
                .collect(Collectors.toList());
    }
    @Override
    public RetrieveProjectQueryResponse detailProject(UUID projectId) {
        var retrievedProjectOptional = projectRepository.findById(projectId);
        if (retrievedProjectOptional.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.NOT_FOUND_BY_ID);
        }
        var retrievedProject = retrievedProjectOptional.get();
        return RetrieveProjectQueryResponse.builder()
                .projectId(retrievedProject.getId())
                .projectName(retrievedProject.getName())
                .projectStartAt(retrievedProject.getStartAt())
                .projectEndAt(retrievedProject.getEndAt())
                .projectDuration(retrievedProject.getDuration())
                .projectCreatorId(retrievedProject.getCreatorId())
                .build();
    }

    @Override
    public void deleteProject(UUID projectId) {
        List<Task> listTasks = taskRepository.findAllByProjectId(projectId);
        taskRepository.deleteAll(listTasks);
        projectRepository.deleteById(projectId);
    }
}
