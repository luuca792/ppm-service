package com.ctu.se.oda.model11.mappers.task;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.enums.TaskStatus;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.task.UpdateTaskRequest;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UpdateTaskRequestMapper implements IMainMapper<UpdateTaskRequest, UpdateTaskCommandRequest> {

	@Override
	public UpdateTaskCommandRequest convert(UpdateTaskRequest source) {
		return UpdateTaskCommandRequest.builder()
				.taskId(UUID.fromString(source.getTaskId()))
				.taskName(Optional.ofNullable(source.getTaskName()).orElse(null))
				.taskDescription(source.getTaskDescription())
				.taskStartAt(source.getTaskStartAt())
				.taskEndAt(source.getTaskEndAt())
				.taskDuration(source.getTaskDuration())
				.taskStatus(Optional.ofNullable(source.getTaskStatus()).map(TaskStatus::valueOf).orElse(null))
				.projectId(Optional.ofNullable(source.getProjectId()).map(UUID::fromString).orElse(null)).build();
	}
}
