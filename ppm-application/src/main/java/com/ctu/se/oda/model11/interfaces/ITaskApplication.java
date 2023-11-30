package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.dtos.TaskDTO;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;

import java.util.List;
import java.util.UUID;

public interface ITaskApplication {
	void createTask(CreateTaskCommandRequest createTaskCommandRequest);
	void updateTask(UpdateTaskCommandRequest updateTaskCommandRequest);
	List<RetrieveTaskQueryResponse> listTask(String projectId);
	RetrieveTaskQueryResponse detailTask(UUID taskId);
	TaskDTO getTaskById(UUID taskId);
	void addMaterialToTask(UUID taskId, UUID materialId, Double amount);
	void deleteMaterialFromTask(UUID taskId, UUID materialId);
	void updateMaterialToTask(UUID taskId, UUID materialId, Double amount);
	void deleteTask(UUID taskId);
	List<TaskDTO> getTasksOfProject(UUID projectId);
	List<RetrieveTaskQueryResponse> getAllDependentTasks(UUID taskId);
}
