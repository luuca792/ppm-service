package com.ctu.se.oda.model11.models.queries.responses.task;


import com.ctu.se.oda.model11.daos.ITaskStatusService;
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

    private ITaskStatusService taskStatus;

    public RetrieveTaskQueryResponse(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, ITaskStatusService taskStatus) {
        taskId = taskId;
        taskName = taskName;
        taskDescription = taskDescription;
        taskStartAt = taskStartAt;
        taskEndAt = taskEndAt;
        taskStatus = taskStatus;
    }
}
