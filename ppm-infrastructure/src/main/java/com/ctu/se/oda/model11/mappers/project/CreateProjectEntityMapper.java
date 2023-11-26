package com.ctu.se.oda.model11.mappers.project;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.enums.ProjectStatus;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CreateProjectEntityMapper implements IInfrastructureMapper<CreateProjectCommandRequest, Project>{

    @Override
    public Project convert(CreateProjectCommandRequest source) {
        return Project.builder()
                .name(source.getProjectName())
                .duration(source.getProjectDuration())
                .status(ProjectStatus.PENDING)
                .userId(source.getProjectCreatorId())
                .startAt(source.getProjectStartAt())
                .endAt(source.getProjectEndAt())
                .isTemplate(true)
                .build();
    }
}
