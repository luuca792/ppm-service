package com.ctu.se.oda.model11.models.commands.responses.task;

import com.ctu.se.oda.model11.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
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
    private UUID projectId;
    private UUID taskParentId;
    private List<CreateTaskCommandResponse> subtasks;

    public CreateTaskCommandResponse(UUID taskId, String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt, TaskStatus taskStatus, UUID projectId, UUID taskParentId, List<CreateTaskCommandResponse> subtasks) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
        this.projectId = projectId;
        this.taskStatus = taskStatus;
        this.taskParentId = taskParentId;
        this.subtasks = subtasks;
    }

}
