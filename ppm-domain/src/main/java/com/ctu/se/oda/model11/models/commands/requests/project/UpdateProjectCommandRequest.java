package com.ctu.se.oda.model11.models.commands.requests.project;

import com.ctu.se.oda.model11.enums.ProjectStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class UpdateProjectCommandRequest {
    private UUID projectId;
    @NotBlank
    @Size(max = 250)
    private String projectName;
    @Nullable
    private LocalDate projectStartAt;
    @Nullable
    private LocalDate projectEndAt;
    @Positive
    @NotNull
    private Double projectDuration;
    @NotNull
    private ProjectStatus projectStatus;
    @NotNull
    private UUID projectCreatorId;

    public UpdateProjectCommandRequest(UUID projectId, String projectName, LocalDate projectStartAt, LocalDate projectEndAt, Double projectDuration, ProjectStatus projectStatus, UUID projectCreatorId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectStartAt = projectStartAt;
        this.projectEndAt = projectEndAt;
        this.projectDuration = projectDuration;
        this.projectStatus = projectStatus;
        this.projectCreatorId = projectCreatorId;
    }

    @Override
    public String toString() {
        return "UpdateProjectCommandRequest{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectStartAt=" + projectStartAt +
                ", projectEndAt=" + projectEndAt +
                ", projectDuration=" + projectDuration +
                ", projectStatus=" + projectStatus +
                ", projectCreatorId=" + projectCreatorId +
                '}';
    }
}
