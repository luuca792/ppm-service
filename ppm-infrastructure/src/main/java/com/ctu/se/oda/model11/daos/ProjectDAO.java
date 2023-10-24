package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.daos.IProjectService;
import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.ProjectStatus;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.project.UpdateProjectCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import com.ctu.se.oda.model11.repositories.IProjectRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Validated
public class ProjectDAO implements IProjectService {
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IInfrastructureMapper<CreateProjectCommandRequest, Project, CreateProjectCommandResponse> createProjectEntityMapper;
    @Autowired
    private IInfrastructureMapper<UpdateProjectCommandRequest, Project, UpdateProjectCommandResponse> updateProjectEntityMapper;

    @Override
    public CreateProjectCommandResponse createProject(@Valid CreateProjectCommandRequest createProjectCommandRequest) {
        return this.createProjectEntityMapper.reverse(
                this.projectRepository.save(this.createProjectEntityMapper.convert(createProjectCommandRequest))
        );
    }
    @Override
    public UpdateProjectCommandResponse updateProject(@Valid UpdateProjectCommandRequest updateProjectCommandRequest, UUID projectId) {
        updateProjectCommandRequest.setProjectId(projectId);
        return this.updateProjectEntityMapper.reverse(
                this.projectRepository.save(this.updateProjectEntityMapper.convert(updateProjectCommandRequest))
        );
    }
    @Override
    public List<RetrieveProjectQueryResponse> listProject() {
        return this.projectRepository.findAll().stream()
                .map(project -> RetrieveProjectQueryResponse.builder()
                        .projectId(project.getId())
                        .projectName(project.getName())
                        .projectDuration(project.getDuration())
                        .projectCreatorId(project.getCreatorId())
                        .projectStatus(project.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
    @Override
    public RetrieveProjectQueryResponse detailProject(UUID projectId) {
        var retrievedProjectOptional = this.projectRepository.findById(projectId);
        if (retrievedProjectOptional.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.NOT_FOUND_BY_ID);
        }
        var retrievedProject = retrievedProjectOptional.get();
        var showDetailProject = RetrieveProjectQueryResponse.builder()
                .projectId(retrievedProject.getId())
                .projectName(retrievedProject.getName())
                .projectDuration(retrievedProject.getDuration())
                .projectCreatorId(retrievedProject.getCreatorId())
                .projectStatus(retrievedProject.getStatus())
                .build();
        return showDetailProject;
    }

    @Override
    public void deleteProject(UUID projectId) {
        this.projectRepository.deleteById(projectId);
    }
}
