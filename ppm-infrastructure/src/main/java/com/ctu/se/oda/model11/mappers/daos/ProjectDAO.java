package com.ctu.se.oda.model11.mappers.daos;

import com.ctu.se.oda.model11.daos.IProjectService;
import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.repositories.IProjectRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ProjectDAO implements IProjectService {
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IInfrastructureMapper projectMapper;
    @Override
    public CreateProjectCommandResponse createProject(CreateProjectCommandRequest createProjectCommandRequest) {
        Project newProject = (Project) this.projectMapper.mapping(createProjectCommandRequest);
        Project createProject = this.projectRepository.save(newProject);
        return (CreateProjectCommandResponse) this.projectMapper.reverse(createProject);
    }
}
