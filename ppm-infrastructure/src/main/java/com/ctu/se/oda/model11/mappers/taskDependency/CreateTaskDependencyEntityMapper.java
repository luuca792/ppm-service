package com.ctu.se.oda.model11.mappers.taskDependency;

import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.entities.TaskDependency;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.repositories.ITaskRepository;

import lombok.NoArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateTaskDependencyEntityMapper
		implements IInfrastructureMapper<CreateTaskDependencyCommandRequest, TaskDependency> {

	@Autowired
	private ITaskRepository taskRepository;

	@Override
	public TaskDependency convert(CreateTaskDependencyCommandRequest source) {
		Optional<Task> task = taskRepository.findById(source.getTaskId());
		Optional<Task> dependentTask = taskRepository.findById(source.getDependentTaskId());
		if (!task.isPresent() || !dependentTask.isPresent()) {
			throw new InternalServerErrorException(CustomErrorMessage.TASK_ID_DO_NOT_EXIST);
		}
		return TaskDependency.builder()
				.taskId(task.get())
				.dependentTaskId(dependentTask.get())
				.dependencyType(source.getDependencyType())
				.build();
	}
}
