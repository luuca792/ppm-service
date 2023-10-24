package com.ctu.se.oda.model11.models.commands.requests.project;

import com.ctu.se.oda.model11.daos.IProjectStatusService;
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
    @NotNull
    private IProjectStatusService projectStatus;
    public UpdateProjectCommandRequest(UUID projectId, String projectName, Double projectDuration, UUID projectCreatorId, IProjectStatusService projectStatus) {
        projectId = projectId;
        projectName = projectName;
        projectDuration = projectDuration;
        projectCreatorId = projectCreatorId;
        projectStatus = projectStatus;
    }

    @Override
    public String toString() {
        return "UpdateProjectCommandRequest{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDuration=" + projectDuration +
                ", projectCreatorId=" + projectCreatorId +
                ", projectStatus=" + projectStatus +
                '}';
    }
}
