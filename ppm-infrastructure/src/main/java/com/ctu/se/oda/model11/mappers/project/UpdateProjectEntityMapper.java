package com.ctu.se.oda.model11.mappers.project;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor

public class UpdateProjectEntityMapper implements IInfrastructureMapper<UpdateProjectCommandRequest, Project> {

    @Override
    public Project convert(UpdateProjectCommandRequest source) {
        return Project.builder()
                .id(source.getProjectId())
                .name(source.getProjectName())
                .duration(source.getProjectDuration())
                .status(source.getProjectStatus())
                .creatorId(source.getProjectCreatorId())
                .build();
    }

}
