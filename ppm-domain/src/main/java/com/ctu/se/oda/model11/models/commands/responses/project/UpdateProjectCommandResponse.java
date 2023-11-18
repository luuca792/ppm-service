package com.ctu.se.oda.model11.models.commands.responses.project;

import com.ctu.se.oda.model11.enums.ProjectStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class UpdateProjectCommandResponse {
    private UUID projectId;
    private String projectName;
    private LocalDate projectStartAt;
    private LocalDate projectEndAt;
    private Double projectDuration;
    private ProjectStatus projectStatus;
    private UUID projectCreatorId;

    public UpdateProjectCommandResponse(UUID projectId, String projectName, LocalDate projectStartAt, LocalDate projectEndAt, Double projectDuration, ProjectStatus projectStatus, UUID projectCreatorId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectStartAt = projectStartAt;
        this.projectEndAt = projectEndAt;
        this.projectDuration = projectDuration;
        this.projectStatus = projectStatus;
        this.projectCreatorId = projectCreatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateProjectCommandResponse that = (UpdateProjectCommandResponse) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(projectName, that.projectName) && Objects.equals(projectStartAt, that.projectStartAt) && Objects.equals(projectEndAt, that.projectEndAt) && Objects.equals(projectDuration, that.projectDuration) && Objects.equals(projectStatus, that.projectStatus) && Objects.equals(projectCreatorId, that.projectCreatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, projectStartAt, projectEndAt, projectDuration, projectStatus, projectCreatorId);
    }
}
