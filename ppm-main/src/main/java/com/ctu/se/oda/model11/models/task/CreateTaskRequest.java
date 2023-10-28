package com.ctu.se.oda.model11.models.task;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CreateTaskRequest {
    private String taskName;
    private String taskDescription;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;
    private String projectId;
    private String taskParentId;
}