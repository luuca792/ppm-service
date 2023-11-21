package com.ctu.se.oda.model11.interfaces;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.taskDependency.RetrieveTaskDependencyQueryResponse;

public interface ITaskDependencyApplication {
	void createTaskDependency(CreateTaskDependencyCommandRequest request);
	
	List<RetrieveTaskDependencyQueryResponse> getAllTaskDependencies();
	
	void deleteTaskDependency(UUID id);
}
