package com.ctu.se.oda.model11.models.project;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProjectRequest {

    private String projectId;

    private String projectName;

    private LocalDate projectStartAt;

    private LocalDate projectEndAt;

    private Double projectDuration;

    private String projectStatus;

    private String projectCreatorId;
}
