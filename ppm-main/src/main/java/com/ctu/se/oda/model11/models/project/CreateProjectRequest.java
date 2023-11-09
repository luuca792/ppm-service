package com.ctu.se.oda.model11.models.project;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CreateProjectRequest {

    private String projectName;

    private LocalDate projectStartAt;

    private LocalDate projectEndAt;

    private Double projectDuration;

    private UUID projectCreatorId;

}
