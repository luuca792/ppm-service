package com.ctu.se.oda.model11.models.taskDependency;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTaskDependencyRequest {
	private String taskId;
	private String dependentTaskId;
	private String dependencyType;
}
