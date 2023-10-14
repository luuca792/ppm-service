package com.ctu.se.oda.model11.models.commands.requests.task;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class UpdateTaskCommandRequest {
    private UUID taskId;
    private String taskName;
    private String taskDescription;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;

    public UpdateTaskCommandRequest(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
    }

    @Override
    public String toString() {
        return "UpdateTaskCommandRequest{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStartAt=" + taskStartAt +
                ", taskEndAt=" + taskEndAt +
                '}';
    }
}
