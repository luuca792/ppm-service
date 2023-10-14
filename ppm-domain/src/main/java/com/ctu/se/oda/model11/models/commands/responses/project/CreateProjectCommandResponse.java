package com.ctu.se.oda.model11.models.commands.responses.project;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDuration = projectDuration;
        this.projectCreatorId = projectCreatorId;
    }
}
