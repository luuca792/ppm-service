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
<<<<<<< HEAD
    private Double taskDuration;
=======
    private String projectId;
    private String taskParentId;
>>>>>>> b8ea77291b6e5ab6464cc72f1baf77bb0c478f37
}
