package com.ctu.se.oda.model11.models.queries.responses.project;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class RetrieveProjectQueryResponse {
    private UUID projectId;
    private String projectName;
    private LocalDate projectStartAt;
    private LocalDate projectEndAt;
    private Double projectDuration;
    private UUID projectCreatorId;

    public RetrieveProjectQueryResponse(UUID projectId, String projectName, LocalDate projectStartAt, LocalDate projectEndAt, Double projectDuration, UUID projectCreatorId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectStartAt = projectStartAt;
        this.projectEndAt = projectEndAt;
        this.projectDuration = projectDuration;
        this.projectCreatorId = projectCreatorId;
    }
}
