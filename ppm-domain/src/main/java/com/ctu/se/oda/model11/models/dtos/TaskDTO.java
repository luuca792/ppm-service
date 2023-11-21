package com.ctu.se.oda.model11.models.dtos;

import com.ctu.se.oda.model11.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class TaskDTO {
	private UUID taskId;
	private String taskName;
	private String taskDescription;
	private LocalDate taskStartAt;
	private LocalDate taskEndAt;
	private Double taskDuration;
	private TaskStatus taskStatus;
	private UUID projectId;
}
