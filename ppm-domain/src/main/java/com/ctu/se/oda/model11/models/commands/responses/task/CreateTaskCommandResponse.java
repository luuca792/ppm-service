package com.ctu.se.oda.model11.models.commands.responses.task;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateTaskCommandResponse {
    private UUID taskId;
    private String taskName;
    private String taskDescription;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;
    private UUID projectId;

    public CreateTaskCommandResponse(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, UUID projectId) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
        this.projectId = projectId;
    }
}
