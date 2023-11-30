package com.ctu.se.oda.model11;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.daos.ITaskService;
import com.ctu.se.oda.model11.interfaces.ITaskApplication;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.dtos.TaskDTO;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class TaskApplication implements ITaskApplication {

	@Autowired
	private ITaskService taskService;

	@Override
	public void createTask(CreateTaskCommandRequest createTaskCommandRequest) {
		taskService.createTask(createTaskCommandRequest);
	}

	@Override
	public void updateTask(UpdateTaskCommandRequest updateTaskCommandRequest) {
		taskService.updateTask(updateTaskCommandRequest);
	}

	@Override
	public List<RetrieveTaskQueryResponse> listTask(String projectId) {
		return taskService.getAllTasks(Optional.ofNullable(projectId).map(UUID::fromString).orElse(null));
	}

	@Override
	public RetrieveTaskQueryResponse detailTask(UUID taskId) {
		return taskService.detailTask(taskId);
	}

	@Override
	public TaskDTO getTaskById(UUID taskId) {
		return taskService.getTaskById(taskId);
	}

	@Override
	public void addMaterialToTask(UUID taskId, UUID materialId, Double amount) {
		taskService.addMaterialToTask(taskId, materialId, amount);
	}

	@Override
	public void deleteMaterialFromTask(UUID taskId, UUID materialId) {
		taskService.deleteMaterialFromTask(taskId, materialId);
	}

	@Override
	public void updateMaterialToTask(UUID taskId, UUID materialId, Double amount) {
		taskService.updateMaterialToTask(taskId, materialId, amount);
	}

	@Override
	public void deleteTask(UUID taskId) {
		taskService.deleteTask(taskId);
	}

	@Override
	public List<TaskDTO> getTasksOfProject(UUID projectId) {
		return taskService.getTasksOfProject(projectId);
	}

	@Override
	public List<RetrieveTaskQueryResponse> getAllDependentTasks(UUID taskId) {
		return taskService.getAllDependentTasks(taskId);
	}
}
