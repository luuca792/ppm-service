package com.ctu.se.oda.model11.models.commands.requests.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class UpdateProjectCommandRequest {
    private UUID projectId;
    @NotBlank
    @Size(max = 250)
    private String projectName;
    @Positive
    @NotNull
    private Double projectDuration;
    @NotNull
    private UUID projectCreatorId;

    public UpdateProjectCommandRequest(UUID projectId, String projectName, Double projectDuration, UUID projectCreatorId) {
        projectId = projectId;
        projectName = projectName;
        projectDuration = projectDuration;
        projectCreatorId = projectCreatorId;
    }

    @Override
    public String toString() {
        return "UpdateProjectCommandRequest{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDuration=" + projectDuration +
                ", projectCreatorId=" + projectCreatorId +
                '}';
    }
}
