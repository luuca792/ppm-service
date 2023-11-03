package com.ctu.se.oda.model11.models.queries.responses.task;


import com.ctu.se.oda.model11.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class RetrieveTaskQueryResponse {
    private UUID taskId;
    private String taskName;
    private String taskDescription;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;
    private Double taskDuration;
    private TaskStatus taskStatus;
    private UUID projectId;
    private UUID taskParentId;
    private List<RetrieveTaskQueryResponse> subtasks;

    public RetrieveTaskQueryResponse(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, Double taskDuration, TaskStatus taskStatus, UUID projectId, UUID taskParentId, List<RetrieveTaskQueryResponse> subtasks) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.projectId = projectId;
        this.taskParentId = taskParentId;
        this.subtasks = subtasks;
    }
}
