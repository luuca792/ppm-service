package com.ctu.se.oda.model11.mappers.project;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CreateProjectEntityMapper implements IInfrastructureMapper<CreateProjectCommandRequest, Project>{

    @Override
    public Project convert(CreateProjectCommandRequest source) {
        return new Project(
                source.getProjectName(),
                source.getProjectStartAt(),
                source.getProjectEndAt(),
                source.getProjectDuration(),
                source.getProjectCreatorId()
        );
    }

}
