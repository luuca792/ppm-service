package com.ctu.se.oda.model11.models.commands.responses.project;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateProjectCommandResponse {
    private UUID projectId;
    private String projectName;
    private LocalDate projectStartAt;
    private LocalDate projectEndAt;
    private Double projectDuration;
    private UUID projectCreatorId;

    public CreateProjectCommandResponse(UUID projectId, String projectName, LocalDate projectStartAt, LocalDate projectEndAt, Double projectDuration, UUID projectCreatorId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectStartAt = projectStartAt;
        this.projectEndAt = projectEndAt;
        this.projectDuration = projectDuration;
        this.projectCreatorId = projectCreatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProjectCommandResponse that = (CreateProjectCommandResponse) o;
        return projectName.equals(that.projectName) && projectStartAt.equals(that.projectStartAt) && projectEndAt.equals(that.projectEndAt) && projectDuration.equals(that.projectDuration) && projectCreatorId.equals(that.projectCreatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, projectStartAt, projectEndAt, projectDuration, projectCreatorId);
    }
}
