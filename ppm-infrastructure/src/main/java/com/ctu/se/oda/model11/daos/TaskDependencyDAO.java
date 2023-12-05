package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.entities.TaskDependency;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.dtos.TaskDependencyDTO;
import com.ctu.se.oda.model11.models.queries.responses.taskDependency.RetrieveTaskDependencyQueryResponse;
import com.ctu.se.oda.model11.repositories.ITaskDependencyRepository;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Validated
public class TaskDependencyDAO implements ITaskDependencyService {

	@Autowired
	private ITaskDependencyRepository taskDependencyRepository;

	@Autowired
	private ITaskRepository taskRepository;

	@Autowired
	private IInfrastructureMapper<CreateTaskDependencyCommandRequest, TaskDependency> mapper;

	@Override
	public void addDependency(CreateTaskDependencyCommandRequest request) {
		var task = taskRepository.findById(request.getTaskId());
		var dependentTask = taskRepository.findById(request.getDependentTaskId());
		if (task.isEmpty() || dependentTask.isEmpty()) {
			throw new InternalServerErrorException(CustomErrorMessage.TASK_ID_NOT_FOUND);
		}
		taskDependencyRepository.save(mapper.convert(request));
	}

	@Override
	public void deleteTaskDependencyById(UUID dependencyId) {
		Optional<TaskDependency> dependency = taskDependencyRepository.findById(dependencyId);
		if (!dependency.isPresent()) {
			throw new InternalServerErrorException(CustomErrorMessage.TASK_DEPENDENCY_ID_NOT_FOUND);
		}
		taskDependencyRepository.deleteById(dependencyId);
	}

	@Override
	public List<TaskDependencyDTO> getDependentTasks(UUID taskId) {
		Optional<Task> task = taskRepository.findById(taskId);
		if (!task.isPresent()) {
			throw new InternalServerErrorException(CustomErrorMessage.TASK_ID_NOT_FOUND);
		}
		List<TaskDependency> dependencies = taskDependencyRepository.findAllByTaskId(task.get());
		return dependencies.stream()
				.map(dependency -> TaskDependencyDTO.builder()
					.taskId(dependency.getTaskId().getId())
					.dependentTaskId(dependency.getDependentTaskId().getId())
					.dependencyType(dependency.getDependencyType()).build())
				.collect(Collectors.toList());
	}

	@Override
	public List<RetrieveTaskDependencyQueryResponse> getAllTaskDependencies(UUID taskId) {

		return taskDependencyRepository.findAll().stream()
				.filter(dependency -> Objects.isNull(taskId) || dependency.getTaskId().getId().equals(taskId))
				.map(dependency -> RetrieveTaskDependencyQueryResponse.builder()
								.id(dependency.getId())
								.taskId(dependency.getTaskId().getId())
								.taskDependentId(dependency.getDependentTaskId().getId())
								.dependencyType(dependency.getDependencyType())
								.build())
				.collect(Collectors.toList());
	}

}
