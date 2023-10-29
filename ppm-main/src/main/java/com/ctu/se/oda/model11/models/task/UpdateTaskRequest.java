package com.ctu.se.oda.model11.models.task;

import com.ctu.se.oda.model11.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UpdateTaskRequest {
    private String taskId;
    private String taskName;
    private String taskDescription;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;
    private TaskStatus taskStatus;
}
