package com.ctu.se.oda.model11.models.commands.requests.task;

import com.ctu.se.oda.model11.daos.ITaskStatusService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    private ITaskStatusService taskStatus;

    public UpdateTaskCommandRequest(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, ITaskStatusService taskStatus) {
        taskId = taskId;
        taskName = taskName;
        taskDescription = taskDescription;
        taskStartAt = taskStartAt;
        taskEndAt = taskEndAt;
        taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "UpdateTaskCommandRequest{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStartAt=" + taskStartAt +
                ", taskEndAt=" + taskEndAt +
                ", taskStatus=" + taskStatus +
                '}';
    }

}
