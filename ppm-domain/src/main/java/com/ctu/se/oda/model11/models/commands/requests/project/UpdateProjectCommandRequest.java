package com.ctu.se.oda.model11.models.commands.requests.project;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class UpdateProjectCommandRequest {
    private UUID projectId;
    private String projectName;
    private Double projectDuration;
    private UUID projectCreatorId;

    public UpdateProjectCommandRequest(UUID projectId, String projectName, Double projectDuration, UUID projectCreatorId) {
        this.projectName = projectName;
        this.projectDuration = projectDuration;
        this.projectCreatorId = projectCreatorId;
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
