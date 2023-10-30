package com.ctu.se.oda.model11.models.commands.requests.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class UpdateTaskCommandRequest {

    private UUID taskId;

    @Size(max = 250)
    private String taskName;

    @Size(max = 500)
    private String taskDescription;

    private LocalDate taskStartAt;

    private LocalDate taskEndAt;
    
    @NotNull
    private Double taskDuration;

    private UUID projectId;

    private UUID taskParentId;

    public UpdateTaskCommandRequest(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt,
        LocalDate taskEndAt, UUID projectId, UUID taskParentId, Double taskDuration) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
        this.taskDuration = taskDuration;
        this.projectId = projectId;
        this.taskParentId = taskParentId;
    }

    @Override
    public String toString() {
        return "UpdateTaskCommandRequest{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStartAt=" + taskStartAt +
                ", taskEndAt=" + taskEndAt +
                ", taskDuration=" + taskDuration +
                ", projectId=" + projectId +
                ", taskParentId=" + taskParentId +
                '}';
    }

    public UUID getTaskParentId() {
        return taskParentId;
    }

    public void setTaskParentId(UUID taskParentId) {
        this.taskParentId = taskParentId;
    }
}
