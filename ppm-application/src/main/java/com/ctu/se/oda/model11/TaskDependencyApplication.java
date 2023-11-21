package com.ctu.se.oda.model11;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.daos.ITaskDependencyService;
import com.ctu.se.oda.model11.interfaces.ITaskDependencyApplication;
import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.taskDependency.RetrieveTaskDependencyQueryResponse;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class TaskDependencyApplication implements ITaskDependencyApplication {

	@Autowired
	private ITaskDependencyService taskDependencyService;

	@Override
	public void createTaskDependency(CreateTaskDependencyCommandRequest request) {
		taskDependencyService.addDependency(request);
	}

	@Override
	public List<RetrieveTaskDependencyQueryResponse> getAllTaskDependencies() {
		return taskDependencyService.getAllTaskDependences();
	}

	@Override
	public void deleteTaskDependency(UUID id) {
		taskDependencyService.deleteTaskDependencyById(id);
	}
}
