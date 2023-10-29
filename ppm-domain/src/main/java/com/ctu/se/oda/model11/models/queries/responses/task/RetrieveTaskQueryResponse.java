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
    private UUID projectId;
    private TaskStatus taskStatus;
    private UUID taskParentId;
    private List<RetrieveTaskQueryResponse> subtasks;

    public RetrieveTaskQueryResponse(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, TaskStatus taskStatus, UUID projectId, UUID taskParentId, List<RetrieveTaskQueryResponse> subtasks) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
        this.taskStatus = taskStatus;
        this.projectId = projectId;
        this.taskParentId = taskParentId;
        this.subtasks = subtasks;
    }
}
