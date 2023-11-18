package com.ctu.se.oda.model11.mappers.project;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.project.CreateProjectRequest;

import lombok.NoArgsConstructor;

import java.util.UUID;

@Component
@NoArgsConstructor
public class CreateProjectRequestMapper implements IMainMapper<CreateProjectRequest, CreateProjectCommandRequest> {

    @Override
    public CreateProjectCommandRequest convert(CreateProjectRequest source) {
        return CreateProjectCommandRequest.builder()
                .projectName(source.getProjectName())
                .projectStartAt(source.getProjectStartAt())
                .projectEndAt(source.getProjectEndAt())
                .projectDuration(source.getProjectDuration())
                .projectCreatorId(UUID.fromString(source.getProjectCreatorId()))
                .build();
    }
}
