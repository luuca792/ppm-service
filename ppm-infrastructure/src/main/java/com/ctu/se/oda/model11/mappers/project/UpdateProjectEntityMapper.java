package com.ctu.se.oda.model11.mappers.project;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.UpdateProjectCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor

public class UpdateProjectEntityMapper implements IInfrastructureMapper<UpdateProjectCommandRequest, Project, UpdateProjectCommandResponse> {

    @Override
    public Project convert(UpdateProjectCommandRequest source) {
        return new Project(
                source.getProjectId(),
                source.getProjectName(),
                source.getProjectDuration(),
                source.getProjectCreatorId()
        );
    }

    @Override
    public UpdateProjectCommandResponse reverse(Project destination) {
        return UpdateProjectCommandResponse.builder()
                .projectId(destination.getId())
                .projectName(destination.getName())
                .projectDuration(destination.getDuration())
                .projectCreatorId(destination.getCreatorId())
                .build();
    }
}
