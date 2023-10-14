package com.ctu.se.oda.model11.mappers.project;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.project.CreateProjectRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class CreateProjectRequestMapper implements IMainMapper<CreateProjectRequest, CreateProjectCommandRequest> {
    @Override
    public CreateProjectCommandRequest convert(CreateProjectRequest source) {
        return CreateProjectCommandRequest.builder()
                .projectName(source.getProjectName())
                .projectDuration(source.getProjectDuration())
                .projectCreatorId(UUID.fromString(source.getProjectCreatorId()))
                .build();
    }

    @Override
    public CreateProjectRequest reverse(CreateProjectCommandRequest destination) {
        return CreateProjectRequest.builder()
                .projectName(destination.getProjectName())
                .projectDuration(destination.getProjectDuration())
                .projectCreatorId(destination.getProjectCreatorId().toString())
                .build();
    }
}
