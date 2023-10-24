package com.ctu.se.oda.model11.models.project;

import com.ctu.se.oda.model11.entities.ProjectStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateProjectRequest {
    private String projectId;
    private String projectName;
    private Double projectDuration;
    private String projectCreatorId;
    private ProjectStatus projectStatus;
}
