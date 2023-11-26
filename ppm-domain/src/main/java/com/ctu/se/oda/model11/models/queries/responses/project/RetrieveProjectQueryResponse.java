package com.ctu.se.oda.model11.models.queries.responses.project;

import com.ctu.se.oda.model11.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveProjectQueryResponse {
    private UUID projectId;
    private String projectName;
    private LocalDate projectStartAt;
    private LocalDate projectEndAt;
    private Double projectDuration;
    private ProjectStatus projectStatus;
    private Boolean isTemplate;
    private UUID projectCreatorId;
}
