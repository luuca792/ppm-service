package com.ctu.se.oda.model11.models.commands.requests.task;

import java.time.LocalDate;
import java.util.UUID;

import com.ctu.se.oda.model11.enums.TaskStatus;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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

    private Double taskDuration;

    private TaskStatus taskStatus;

    private UUID projectId;

    private UUID taskParentId;

    public UpdateTaskCommandRequest(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt,
        LocalDate taskEndAt, Double taskDuration, TaskStatus taskStatus, UUID projectId, UUID taskParentId) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
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
                ", taskStatus=" + taskStatus +
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
