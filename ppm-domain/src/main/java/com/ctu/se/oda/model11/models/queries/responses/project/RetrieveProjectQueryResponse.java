package com.ctu.se.oda.model11.models.queries.responses.project;

import com.ctu.se.oda.model11.daos.IProjectStatusService;
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
    private UUID projectCreatorId;
    private IProjectStatusService projectStatus;

    public RetrieveProjectQueryResponse(UUID projectId, String projectName, Double projectDuration, UUID projectCreatorId, IProjectStatusService projectStatus) {
        projectId = projectId;
        projectName = projectName;
        projectDuration = projectDuration;
        projectCreatorId = projectCreatorId;
        projectStatus = projectStatus;
    }
}
