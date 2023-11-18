package com.ctu.se.oda.model11.mappers.project;

import com.ctu.se.oda.model11.enums.ProjectStatus;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.project.UpdateProjectRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.Optional;

@Component
@NoArgsConstructor
public class UpdateProjectRequestMapper implements IMainMapper<UpdateProjectRequest, UpdateProjectCommandRequest> {

    @Override
    public UpdateProjectCommandRequest convert(UpdateProjectRequest source) {
        return UpdateProjectCommandRequest.builder()
                .projectId(UUID.fromString(source.getProjectId()))
                .projectName(source.getProjectName())
                .projectStartAt(source.getProjectStartAt())
                .projectEndAt(source.getProjectEndAt())
                .projectDuration(source.getProjectDuration())
                .projectStatus(Optional.ofNullable(source.getProjectStatus()).map(ProjectStatus::valueOf).orElse(null))
                .projectCreatorId(UUID.fromString(source.getProjectCreatorId()))
                .build();
    }
}
