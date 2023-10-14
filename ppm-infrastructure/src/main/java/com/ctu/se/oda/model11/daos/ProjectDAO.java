package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.daos.IProjectService;
import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.project.UpdateProjectCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import com.ctu.se.oda.model11.repositories.IProjectRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class ProjectDAO implements IProjectService {
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IInfrastructureMapper<CreateProjectCommandRequest, Project, CreateProjectCommandResponse> createProjectEntityMapper;
    @Autowired
    private IInfrastructureMapper<UpdateProjectCommandRequest, Project, UpdateProjectCommandResponse> updateProjectEntityMapper;

    @Override
    public CreateProjectCommandResponse createProject(CreateProjectCommandRequest createProjectCommandRequest) {
        return this.createProjectEntityMapper.reverse(
                this.projectRepository.save(this.createProjectEntityMapper.convert(createProjectCommandRequest))
        );
    }
    @Override
    public UpdateProjectCommandResponse updateProject(UpdateProjectCommandRequest updateProjectCommandRequest, UUID projectId) {
        updateProjectCommandRequest.setProjectId(projectId);
        return this.updateProjectEntityMapper.reverse(
                this.projectRepository.save(this.updateProjectEntityMapper.convert(updateProjectCommandRequest))
        );
    }
    @Override
    public List<RetrieveProjectQueryResponse> listProject() {
        return this.projectRepository.findAll().stream()
                .map(project -> RetrieveProjectQueryResponse.builder().projectId(project.getId()).projectName(project.getName()).projectDuration(project.getDuration()).projectCreatorId(project.getCreatorId()).build())
                .collect(Collectors.toList());
    }
    @Override
    public RetrieveProjectQueryResponse detailProject(UUID projectId) {
        var optionalRetrievedProject = this.projectRepository.findById(projectId);
        if (optionalRetrievedProject.isEmpty()) {
            throw new NullPointerException();
        }
        var retrievedProject = optionalRetrievedProject.get();
        return RetrieveProjectQueryResponse.builder()
                .projectId(retrievedProject.getId())
                .projectName(retrievedProject.getName())
                .projectDuration(retrievedProject.getDuration())
                .projectCreatorId(retrievedProject.getCreatorId())
                .build();
    }

    @Override
    public void deleteProject(UUID projectId) {
        this.projectRepository.deleteById(projectId);
    }
}
