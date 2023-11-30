package com.ctu.se.oda.model11.daos;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.dtos.TaskDTO;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;

import jakarta.validation.Valid;

public interface ITaskService {
	void createTask(@Valid CreateTaskCommandRequest createTaskCommandRequest);
	void updateTask(@Valid UpdateTaskCommandRequest updateTaskCommandRequest);
	List<RetrieveTaskQueryResponse> getAllTasks(UUID projectId);
	RetrieveTaskQueryResponse detailTask(UUID taskId);
	TaskDTO getTaskById(UUID taskId);
	void addMaterialToTask(UUID taskId, UUID materialId, Double amount);
	void deleteMaterialFromTask(UUID taskId, UUID materialId);
	void updateMaterialToTask(UUID taskId, UUID materialId, Double amount);
	void deleteTask(UUID taskId);
	List<TaskDTO> getTasksOfProject(UUID projectId);
	List<RetrieveTaskQueryResponse> getAllDependentTasks(UUID taskId);
}
