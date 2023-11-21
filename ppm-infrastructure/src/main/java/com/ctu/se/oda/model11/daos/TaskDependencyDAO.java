package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.TaskDependency;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.taskDependency.CreateTaskDependencyCommandRequest;
import com.ctu.se.oda.model11.models.dtos.TaskDependencyDTO;
import com.ctu.se.oda.model11.repositories.ITaskDependencyRepository;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Validated
public class TaskDependencyDAO implements ITaskDependencyService{

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
            throw new InternalServerErrorException(CustomErrorMessage.TASK_ID_DO_NOT_EXIST);
        }
        taskDependencyRepository.save(mapper.convert(request));
    }

    @Override
    public void deleteDependencyById(UUID dependencyId) {
        taskDependencyRepository.deleteById(dependencyId);
    }

    @Override
    public List<TaskDependencyDTO> getDependentTasks(UUID taskId) {
        List<TaskDependency> dependencies = taskDependencyRepository.findAllByTaskId(taskId);
        return dependencies.stream()
                .map(dependency -> TaskDependencyDTO.builder()
                        .taskId(dependency.getTaskId())
                        .dependentTaskId(dependency.getDependentTaskId())
                        .dependencyType(dependency.getDependencyType())
                        .build())
                .collect(Collectors.toList());
    }

}
