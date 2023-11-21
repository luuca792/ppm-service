package com.ctu.se.oda.model11.models.queries.responses.taskDependency;

import java.util.UUID;

import com.ctu.se.oda.model11.enums.DependencyType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RetrieveTaskDependencyQueryResponse {
	private UUID id;
	private UUID taskId;
	private UUID taskDependentId;
	private DependencyType dependencyType;
}
