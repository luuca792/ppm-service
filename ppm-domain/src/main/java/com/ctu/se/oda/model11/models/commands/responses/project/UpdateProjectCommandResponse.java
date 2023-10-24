package com.ctu.se.oda.model11.models.commands.responses.project;

import com.ctu.se.oda.model11.daos.IProjectStatusService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class UpdateProjectCommandResponse {
    private UUID projectId;
    private String projectName;
    private Double projectDuration;
    private UUID projectCreatorId;
    private IProjectStatusService projectStatus;

    public UpdateProjectCommandResponse(UUID projectId, String projectName, Double projectDuration, UUID projectCreatorId, IProjectStatusService projectStatus) {
        projectId = projectId;
        projectName = projectName;
        projectDuration = projectDuration;
        projectCreatorId = projectCreatorId;
        projectStatus = projectStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateProjectCommandResponse that = (UpdateProjectCommandResponse) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(projectName, that.projectName) && Objects.equals(projectDuration, that.projectDuration) && Objects.equals(projectCreatorId, that.projectCreatorId) && Objects.equals(projectStatus, that.projectStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, projectDuration, projectCreatorId, projectStatus);
    }
}
