package com.ctu.se.oda.model11.models.queries.responses.task;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
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

    public RetrieveTaskQueryResponse(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, Double taskDuration) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
        this.taskDuration = taskDuration;
    }
}
