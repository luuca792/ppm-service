package com.ctu.se.oda.model11.daos;


import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.project.UpdateProjectCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import com.ctu.se.oda.model11.repositories.IProjectRepository;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Validated
public class ProjectDAO implements IProjectService {
    @Autowired
    private IProjectRepository projectRepository;@Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private IInfrastructureMapper<CreateProjectCommandRequest, Project, CreateProjectCommandResponse> createProjectEntityMapper;
    @Autowired
    private IInfrastructureMapper<UpdateProjectCommandRequest, Project, UpdateProjectCommandResponse> updateProjectEntityMapper;

    @Override
    public CreateProjectCommandResponse createProject(@Valid CreateProjectCommandRequest createProjectCommandRequest) {
        var mappedProject = createProjectEntityMapper.convert(createProjectCommandRequest);
        if (Objects.nonNull(mappedProject.getStartAt()) && Objects.nonNull(mappedProject.getEndAt())) {
            if (mappedProject.getStartAt().isAfter(mappedProject.getEndAt())) {
                throw new IllegalArgumentException(CustomErrorMessage.START_DATE_AFTER_END_DATE);
            }
        }
        return createProjectEntityMapper.reverse(
                projectRepository.save(createProjectEntityMapper.convert(createProjectCommandRequest))
        );
    }
    @Override
    public UpdateProjectCommandResponse updateProject(@Valid UpdateProjectCommandRequest updateProjectCommandRequest) {
        var retrievedProject = projectRepository.findById(updateProjectCommandRequest.getProjectId());
        var mappedProject = updateProjectEntityMapper.convert(updateProjectCommandRequest);
        var creatingProject = retrievedProject.get();
        if (Objects.nonNull(mappedProject.getName())) {
            creatingProject.setName(mappedProject.getName());
        }
        if (Objects.nonNull(mappedProject.getStartAt()) && Objects.nonNull(mappedProject.getEndAt())) {
            if (mappedProject.getStartAt().isAfter(mappedProject.getEndAt())) {
                throw new IllegalArgumentException(CustomErrorMessage.START_DATE_AFTER_END_DATE);
            }
        }
        if (Objects.nonNull(mappedProject.getStartAt())) {
            creatingProject.setStartAt(mappedProject.getStartAt());
        }
        if (Objects.nonNull(mappedProject.getEndAt())) {
            creatingProject.setEndAt(mappedProject.getEndAt());
        }
        if (Objects.nonNull(mappedProject.getDuration())) {
            creatingProject.setDuration(mappedProject.getDuration());
        }
        return updateProjectEntityMapper.reverse(projectRepository.save(retrievedProject.get())
        );
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
