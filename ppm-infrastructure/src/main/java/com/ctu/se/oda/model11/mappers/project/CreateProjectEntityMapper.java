package com.ctu.se.oda.model11.mappers.project;

import com.ctu.se.oda.model11.entities.IEntity;
import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.IDomainModel;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class CreateProjectEntityMapper implements IInfrastructureMapper {
    @Override
    public IEntity mapping(IDomainModel request) {
        var createProjectCommandRequest = (CreateProjectCommandRequest) request;
        var project = new Project();
        project.setName(createProjectCommandRequest.getProjectName());
        project.setDuration(createProjectCommandRequest.getProjectDuration());
        project.setCreatorId(UUID.fromString(createProjectCommandRequest.getProjectCreatorId()));
        return project;
    }

    @Override
    public IDomainModel reverse(IEntity object) {
        var project = (Project) object;
        return new CreateProjectCommandResponse(
                project.getId().toString(),
                project.getName(),
                project.getDuration(),
                project.getCreatorId().toString()
        );
    }
}
