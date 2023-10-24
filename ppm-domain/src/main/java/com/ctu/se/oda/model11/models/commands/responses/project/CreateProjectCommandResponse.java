package com.ctu.se.oda.model11.models.commands.responses.project;

import com.ctu.se.oda.model11.daos.IProjectStatusService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateProjectCommandResponse {
    private UUID projectId;
    private String projectName;
    private Double projectDuration;
    private UUID projectCreatorId;
    private IProjectStatusService projectStatusService;

    public CreateProjectCommandResponse(UUID projectId, String projectName, Double projectDuration, UUID projectCreatorId, IProjectStatusService projectStatusService) {
        projectId = projectId;
        projectName = projectName;
        projectDuration = projectDuration;
        projectCreatorId = projectCreatorId;
        projectStatusService = projectStatusService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProjectCommandResponse that = (CreateProjectCommandResponse) o;
        return projectName.equals(that.projectName) && projectDuration.equals(that.projectDuration) && projectCreatorId.equals(that.projectCreatorId) && projectStatusService.equals(that.projectStatusService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, projectDuration, projectCreatorId, projectStatusService);
    }
}
