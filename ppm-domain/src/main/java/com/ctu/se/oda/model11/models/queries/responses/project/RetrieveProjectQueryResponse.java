package com.ctu.se.oda.model11.models.queries.responses.project;

import com.ctu.se.oda.model11.enums.ProjectStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class RetrieveProjectQueryResponse {
    private UUID projectId;
    private String projectName;
    private Double projectDuration;
    private ProjectStatus projectStatus;
    private UUID projectCreatorId;

    public RetrieveProjectQueryResponse(UUID projectId, String projectName, Double projectDuration, ProjectStatus projectStatus, UUID projectCreatorId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDuration = projectDuration;
        this.projectStatus = projectStatus;
        this.projectCreatorId = projectCreatorId;
    }
}
