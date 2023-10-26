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
    @NotBlank
    @Size(max = 250)
    private String taskName;
    @NotBlank
    @Size(max = 500)
    private String taskDescription;
    @NotNull
    private LocalDate taskStartAt;
    @NotNull
    private LocalDate taskEndAt;
    @NotNull
    private UUID projectId;
    private UUID taskParentId;
    private List<UpdateTaskCommandRequest> subtasks;

    public UpdateTaskCommandRequest(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, UUID projectId, UUID taskParentId, List<UpdateTaskCommandRequest> subtasks) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
        this.projectId = projectId;
        this.taskParentId = taskParentId;
        this.subtasks = subtasks;
    }

    @Override
    public String toString() {
        return "UpdateTaskCommandRequest{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStartAt=" + taskStartAt +
                ", taskEndAt=" + taskEndAt +
                ", projectId=" + projectId +
                ", taskParentId=" + taskParentId +
                ", subtasks=" + subtasks +
                '}';
    }

    public UUID getTaskParentId() {
        return taskParentId;
    }

    public void setTaskParentId(UUID taskParentId) {
        this.taskParentId = taskParentId;
    }

    public List<UpdateTaskCommandRequest> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<UpdateTaskCommandRequest> subtasks) {
        this.subtasks = subtasks;
    }
}
