package com.ctu.se.oda.model11.mappers.project;

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
                .projectId(source.getProjectId())
                .projectName(source.getProjectName())
                .projectDuration(source.getProjectDuration())
                .projectCreatorId(UUID.fromString(source.getProjectCreatorId()))
                .build();
    }

    @Override
    public UpdateProjectRequest reverse(UpdateProjectCommandRequest destination) {
        return UpdateProjectRequest.builder()
                .projectId(destination.getProjectId())
                .projectName(destination.getProjectName())
                .projectDuration(destination.getProjectDuration())
                .projectCreatorId(destination.getProjectCreatorId().toString())
                .build();
    }
}
