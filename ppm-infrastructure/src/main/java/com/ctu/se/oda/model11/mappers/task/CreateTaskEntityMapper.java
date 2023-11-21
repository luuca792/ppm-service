package com.ctu.se.oda.model11.mappers.task;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.enums.TaskStatus;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.repositories.IProjectRepository;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CreateTaskEntityMapper implements IInfrastructureMapper<CreateTaskCommandRequest, Task>{
	
    @Autowired
    private IProjectRepository projectRepository;
    @Override
    public Task convert(CreateTaskCommandRequest source) {
    	Optional<Project> project = projectRepository.findById(source.getProjectId());
		return Task.builder()
    			.name(source.getTaskName())
    			.description(source.getTaskDescription())
    			.startAt(source.getTaskStartAt())
    			.endAt(source.getTaskEndAt())
    			.status(TaskStatus.OPEN)
    			.projectId(project.get())
    			.build();
    }
}
