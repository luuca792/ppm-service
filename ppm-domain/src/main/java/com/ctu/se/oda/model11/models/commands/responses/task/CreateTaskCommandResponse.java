package com.ctu.se.oda.model11.models.commands.responses.task;

import com.ctu.se.oda.model11.enums.TaskStatus;
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

    private TaskStatus taskStatus;

    public CreateTaskCommandResponse(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, TaskStatus taskStatus) {
        taskId = taskId;
        taskName = taskName;
        taskDescription = taskDescription;
        taskStartAt = taskStartAt;
        taskEndAt = taskEndAt;
        taskStatus = taskStatus;
    }

}
