package com.ctu.se.oda.model11.models.task;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UpdateTaskRequest {
    private String taskId;
    private String taskName;
    private String taskDescription;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;
    private String projectId;
    private UUID taskParentId;
    private List<UpdateTaskRequest> subtasks;
}
