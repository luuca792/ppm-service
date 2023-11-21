package com.ctu.se.oda.model11.mappers.task;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.enums.TaskStatus;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.repositories.IProjectRepository;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor

public class UpdateTaskEntityMapper implements IInfrastructureMapper<UpdateTaskCommandRequest, Task> {
	
    @Autowired
    private IProjectRepository projectRepository;
    @Override
    public Task convert(UpdateTaskCommandRequest source) {
    	Optional<Project> project = Optional.ofNullable(source.getProjectId()).map(projectRepository::findById).orElse(null);
		return Task.builder()
				.id(source.getTaskId())
    			.name(source.getTaskName())
    			.description(source.getTaskDescription())
    			.startAt(source.getTaskStartAt())
    			.endAt(source.getTaskEndAt())
    			.duration(source.getTaskDuration())
    			.status(TaskStatus.OPEN)
    			.projectId(Optional.ofNullable(project).map(Optional::get).orElse(null))
    			.build();
    }
}
