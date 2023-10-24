package com.ctu.se.oda.model11.mappers.project;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.ProjectStatus;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.project.UpdateProjectRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class UpdateProjectRequestMapper implements IMainMapper<UpdateProjectRequest, UpdateProjectCommandRequest> {
    @Override
    public UpdateProjectCommandRequest convert(UpdateProjectRequest source) {
        return UpdateProjectCommandRequest.builder()
                .projectName(source.getProjectName())
                .projectDuration(source.getProjectDuration())
                .projectCreatorId(UUID.fromString(source.getProjectCreatorId()))
                .projectStatus(source.getProjectStatus())
                .build();
    }

    @Override
    public UpdateProjectRequest reverse(UpdateProjectCommandRequest destination) {
        return UpdateProjectRequest.builder()
                .projectId(destination.getProjectId().toString())
                .projectName(destination.getProjectName())
                .projectDuration(destination.getProjectDuration())
                .projectCreatorId(destination.getProjectCreatorId().toString())
                .projectName(destination.getProjectStatus().toString())
                .build();
    }
}
