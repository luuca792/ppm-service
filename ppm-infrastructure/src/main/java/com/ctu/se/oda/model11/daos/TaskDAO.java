package com.ctu.se.oda.model11.daos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.Resource;
import com.ctu.se.oda.model11.entities.ResourceMaterial;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.dtos.TaskDTO;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;
import com.ctu.se.oda.model11.repositories.IMaterialRepository;
import com.ctu.se.oda.model11.repositories.IProjectRepository;
import com.ctu.se.oda.model11.repositories.IResourceMaterialRepository;
import com.ctu.se.oda.model11.repositories.IResourceRepository;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import com.ctu.se.oda.model11.utils.ModelMapperUtil;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@Validated
public class TaskDAO implements ITaskService {

	@Autowired
	private IInfrastructureMapper<CreateTaskCommandRequest, Task> createTaskEntityMapper;

	@Autowired
	private IInfrastructureMapper<UpdateTaskCommandRequest, Task> updateTaskEntityMapper;

	@Autowired
	private ITaskRepository taskRepository;

	@Autowired
	private IProjectRepository projectRepository;

	@Autowired
	private IResourceRepository resourceRepository;

	@Autowired
	private IMaterialRepository materialRepository;

	@Autowired
	private IResourceMaterialRepository resourceMaterialRepository;

	@Override
	public void createTask(@Valid CreateTaskCommandRequest createTaskCommandRequest) {
		var retrievedProjectId = projectRepository.findById(createTaskCommandRequest.getProjectId());
		if (retrievedProjectId.isEmpty()) {
			throw new InternalServerErrorException(CustomErrorMessage.PROJECT_ID_NOT_FOUND);
		}
		var retrieveTask = taskRepository.save(createTaskEntityMapper.convert(createTaskCommandRequest));

		Resource resource = new Resource();
		var createdResource = resourceRepository.save(resource);
		retrieveTask.setResource(createdResource);

		taskRepository.save(retrieveTask);
	}

	@Override
	public void updateTask(@Valid UpdateTaskCommandRequest updateTaskCommandRequest) {
		var retrievedTask = taskRepository.findById(updateTaskCommandRequest.getTaskId());
		if (retrievedTask.isEmpty()) {
			throw new IllegalArgumentException(CustomErrorMessage.TASK_ID_NOT_FOUND);
		}
		Task updatedTask = updateTaskEntityMapper.convert(updateTaskCommandRequest);
		ModelMapperUtil.copy(updatedTask, retrievedTask.get());
		taskRepository.save(retrievedTask.get());
	}

	@Override
	public List<RetrieveTaskQueryResponse> getAllTasks() {
		return taskRepository.findAll().stream()
				.map(task -> RetrieveTaskQueryResponse.builder()
						.taskId(task.getId())
						.taskName(task.getName())
						.taskDescription(task.getDescription())
						.taskStartAt(task.getStartAt())
						.taskEndAt(task.getEndAt())
						.taskDuration(task.getDuration())
						.taskStatus(task.getStatus())
						.projectId(task.getProjectId().getId())
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public RetrieveTaskQueryResponse detailTask(UUID taskId) {
		var retrievedTaskOptional = taskRepository.findById(taskId);
		if (retrievedTaskOptional.isEmpty()) {
			throw new InternalServerErrorException(CustomErrorMessage.ENTITY_NOT_FOUND);
		}
		var retrievedTask = retrievedTaskOptional.get();

		return RetrieveTaskQueryResponse.builder()
				.taskId(retrievedTask.getId())
				.taskName(retrievedTask.getName())
				.taskDescription(retrievedTask.getDescription())
				.taskStartAt(retrievedTask.getStartAt())
				.taskEndAt(retrievedTask.getEndAt())
				.taskDuration(retrievedTask.getDuration())
				.taskStatus(retrievedTask.getStatus())
				.projectId(retrievedTask.getProjectId().getId())
				.build();
	}

	@Override
	public void addMaterialToTask(UUID taskId, UUID materialId, Double amount) {
		var retrieveTask = taskRepository.findById(taskId);
		if (retrieveTask.isEmpty()) {
			throw new InternalServerErrorException(CustomErrorMessage.TASK_ID_NOT_FOUND);
		}

		var retrieveResource = retrieveTask.get().getResource();
		ResourceMaterial resourceMaterial = new ResourceMaterial();
		resourceMaterial.setResource(retrieveResource);

		var materialRetrieveOptional = materialRepository.findById(materialId);
		if (materialRetrieveOptional.isEmpty()) {
			throw new InternalServerErrorException(CustomErrorMessage.MATERIAL_ID_NOT_FOUND);
		}

		var materialRetrieve = materialRetrieveOptional.get();
		resourceMaterial.setMaterial(materialRetrieve);
		resourceMaterial.setAmount(amount);

		resourceMaterialRepository.save(resourceMaterial);
	}

	@Override
	public void deleteTask(UUID taskId) {
		var retrieveTask = taskRepository.findById(taskId);
		if (retrieveTask.isEmpty()) {
			throw new InternalServerErrorException(CustomErrorMessage.TASK_ID_NOT_FOUND);
		}
		taskRepository.deleteById(taskId);
	}

	@Override
	public List<TaskDTO> getTasksOfProject(UUID projectId) {
		Optional<Project> project = projectRepository.findById(projectId);
		if (!project.isPresent()) {
			throw new InternalServerErrorException(CustomErrorMessage.PROJECT_ID_NOT_FOUND);
		}
		List<Task> taskList = taskRepository.findAllByProjectId(project.get());
		List<TaskDTO> TaskListDTO = taskList.stream()
				.map(task -> TaskDTO.builder()
						.taskId(task.getId())
						.taskName(task.getName())
						.taskDescription(task.getDescription())
						.taskStartAt(task.getStartAt())
						.taskEndAt(task.getEndAt())
						.taskDuration(task.getDuration())
						.taskStatus(task.getStatus())
						.projectId(project.get().getId())
						.build())
				.collect(Collectors.toList());
		return TaskListDTO;
	}

	@Override
	public TaskDTO getTaskById(UUID taskId) {
		var retrievedTask = taskRepository.findById(taskId);
		if (retrievedTask.isEmpty()) {
			throw new InternalServerErrorException(CustomErrorMessage.ENTITY_NOT_FOUND);
		}
		var task = retrievedTask.get();

		return TaskDTO.builder()
				.taskId(task.getId())
				.taskName(task.getName())
				.taskDescription(task.getDescription())
				.taskStartAt(task.getStartAt())
				.taskEndAt(task.getEndAt())
				.taskDuration(task.getDuration())
				.taskStatus(task.getStatus())
				.projectId(task.getProjectId().getId())
				.build();
	}
}
