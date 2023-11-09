package com.ctu.se.oda.model11.mappers.project;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.enums.ProjectStatus;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateProjectEntityMapper implements IInfrastructureMapper<CreateProjectCommandRequest, Project, CreateProjectCommandResponse>{

    @Override
    public Project convert(CreateProjectCommandRequest source) {
        return new Project(
                source.getProjectName(),
                source.getProjectDuration(),
                ProjectStatus.PENDING,
                source.getProjectCreatorId()
        );
    }

    @Override
    public CreateProjectCommandResponse reverse(Project destination) {
        return new CreateProjectCommandResponse(
                destination.getId(),
                destination.getName(),
                destination.getDuration(),
                destination.getStatus(),
                destination.getCreatorId()
        );
    }
}
