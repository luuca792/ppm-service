package com.ctu.se.oda.model11.models.commands.responses.project;

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

    public CreateProjectCommandResponse(UUID projectId, String projectName, Double projectDuration, UUID projectCreatorId) {
        projectId = projectId;
        projectName = projectName;
        projectDuration = projectDuration;
        projectCreatorId = projectCreatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProjectCommandResponse that = (CreateProjectCommandResponse) o;
        return projectName.equals(that.projectName) && projectDuration.equals(that.projectDuration) && projectCreatorId.equals(that.projectCreatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, projectDuration, projectCreatorId);
    }
}
