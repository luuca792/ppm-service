package com.ctu.se.oda.model11.models.commands.responses.task;

import com.ctu.se.oda.model11.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
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

    private TaskStatus taskStatus;

    public UpdateTaskCommandResponse(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, TaskStatus taskStatus) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
        this.taskStatus = taskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateTaskCommandResponse that = (UpdateTaskCommandResponse) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(taskName, that.taskName) && Objects.equals(taskDescription, that.taskDescription) && Objects.equals(taskStartAt, that.taskStartAt) && Objects.equals(taskEndAt, that.taskEndAt) && Objects.equals(taskStatus, that.taskStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskName, taskDescription, taskStartAt, taskEndAt, taskStatus);
    }
}
