package com.ctu.se.oda.model11.models.project;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateProjectRequest {

    private UUID projectId;

    private String projectName;

    private Double projectDuration;

    private String projectStatus;

    private String projectCreatorId;
}
