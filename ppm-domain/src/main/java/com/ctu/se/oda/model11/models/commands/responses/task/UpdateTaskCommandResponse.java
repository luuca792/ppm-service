package com.ctu.se.oda.model11.models.commands.responses.task;

import com.ctu.se.oda.model11.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class UpdateTaskCommandResponse {
    private UUID taskId;
    private String taskName;
    private String taskDescription;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;
<<<<<<< HEAD
    private Double taskDuration;
=======
    private TaskStatus taskStatus;
>>>>>>> main
    private UUID projectId;
    private UUID taskParentId;
    private List<UpdateTaskCommandResponse> subtasks;

<<<<<<< HEAD
    public UpdateTaskCommandResponse(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, Double taskDuration, UUID projectId, UUID taskParentId, List<UpdateTaskCommandResponse> subtasks) {
=======
    public UpdateTaskCommandResponse(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, TaskStatus taskStatus, UUID projectId, UUID taskParentId, List<UpdateTaskCommandResponse> subtasks) {
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
        this.subtasks = subtasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateTaskCommandResponse that = (UpdateTaskCommandResponse) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(taskName, that.taskName)
                && Objects.equals(taskDescription, that.taskDescription)
                && Objects.equals(taskStartAt, that.taskStartAt)
                && Objects.equals(taskEndAt, that.taskEndAt)
<<<<<<< HEAD
                && Objects.equals(taskDuration, that.taskDuration)
=======
                && Objects.equals(taskStatus, that.taskStatus)
>>>>>>> main
                && Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Objects.hash(taskId, taskName, taskDescription, taskStartAt, taskEndAt, taskDuration, projectId);
=======
        return Objects.hash(taskId, taskName, taskDescription, taskStartAt, taskEndAt, taskStatus, projectId);
>>>>>>> main
    }
}
