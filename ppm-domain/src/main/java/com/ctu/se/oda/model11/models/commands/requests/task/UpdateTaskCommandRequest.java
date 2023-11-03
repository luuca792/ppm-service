package com.ctu.se.oda.model11.models.commands.requests.task;

<<<<<<< HEAD
import jakarta.annotation.Nullable;
=======
import com.ctu.se.oda.model11.enums.TaskStatus;
>>>>>>> main
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

    @Nullable
    private LocalDate taskStartAt;

    @Nullable
    private LocalDate taskEndAt;

<<<<<<< HEAD
    @NotNull
    private Double taskDuration;

    private UUID projectId;

    private UUID taskParentId;

    public UpdateTaskCommandRequest(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt,
        LocalDate taskEndAt, Double taskDuration, UUID projectId, UUID taskParentId) {
=======
    private TaskStatus taskStatus;

    private UUID projectId;
    
    private UUID taskParentId;

    public UpdateTaskCommandRequest(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt,
        LocalDate taskEndAt, TaskStatus taskStatus, UUID projectId, UUID taskParentId) {
>>>>>>> main
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
<<<<<<< HEAD
        this.taskDuration = taskDuration;
=======
        this.taskStatus = taskStatus;
>>>>>>> main
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
<<<<<<< HEAD
                ", taskDuration=" + taskDuration +
=======
                ", taskStatus=" + taskStatus +
>>>>>>> main
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
