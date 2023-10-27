package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;
import com.ctu.se.oda.model11.repositories.IProjectRepository;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Validated
public class TaskDAO implements ITaskService{

    @Autowired
    private IInfrastructureMapper<CreateTaskCommandRequest, Task, CreateTaskCommandResponse> createTaskEntityMapper;

    @Autowired
    private IInfrastructureMapper<UpdateTaskCommandRequest, Task, UpdateTaskCommandResponse> updateTaskEntityMapper;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public CreateTaskCommandResponse createTask(@Valid CreateTaskCommandRequest createTaskCommandRequest) {
        var retrieveProjectId = projectRepository.findById(createTaskCommandRequest.getProjectId());
        if(retrieveProjectId.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.PROJECT_ID_DO_NOT_EXIST);
        }
        return createTaskEntityMapper.reverse(
                taskRepository.save(createTaskEntityMapper.convert(createTaskCommandRequest))
        );
    }

    @Override
    public UpdateTaskCommandResponse updateTask(@Valid UpdateTaskCommandRequest updateTaskCommandRequest) {
        var optionalTask = taskRepository.findById(updateTaskCommandRequest.getTaskId());
        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.TASK_ID_DO_NOT_EXIST);
        }
        var mappedTask = updateTaskEntityMapper.convert(updateTaskCommandRequest);
        var creatingTask = optionalTask.get();
        if (Objects.nonNull(mappedTask.getName())) {
            creatingTask.setName(mappedTask.getName());
        }
        if (Objects.nonNull(mappedTask.getDescription())) {
            creatingTask.setDescription(mappedTask.getDescription());
        }
        if (Objects.nonNull(mappedTask.getStartAt()) && Objects.nonNull(mappedTask.getEndAt())) {
            if (mappedTask.getStartAt().isAfter(mappedTask.getEndAt())) {
                throw new IllegalArgumentException(CustomErrorMessage.START_DATE_BEFORE_END_DATE);
            }
        }
        if (Objects.nonNull(mappedTask.getStartAt())) {
            creatingTask.setStartAt(mappedTask.getStartAt());
        }
        if (Objects.nonNull(mappedTask.getEndAt())) {
            creatingTask.setEndAt(mappedTask.getEndAt());
        }
        return updateTaskEntityMapper.reverse(
                taskRepository.save(creatingTask)
        );
    }

    @Override
    public List<RetrieveTaskQueryResponse> listTask() {
        return taskRepository.findAll().stream().map(
                task -> RetrieveTaskQueryResponse.builder()
                        .taskId(task.getId())
                        .taskName(task.getName())
                        .taskDescription(task.getDescription())
                        .taskStartAt(task.getStartAt())
                        .taskEndAt(task.getEndAt())
                        .projectId(task.getProjectId())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public RetrieveTaskQueryResponse detailTask(UUID taskId) {
        var retrievedTaskOptional = taskRepository.findById(taskId);
        if (retrievedTaskOptional.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.NOT_FOUND_BY_ID);
        }
        var retrievedTask =  retrievedTaskOptional.get();
        return RetrieveTaskQueryResponse.builder()
                .taskId(retrievedTask.getId())
                .taskName(retrievedTask.getName())
                .taskDescription(retrievedTask.getDescription())
                .taskStartAt(retrievedTask.getStartAt())
                .taskEndAt(retrievedTask.getEndAt())
                .projectId(retrievedTask.getProjectId())
                .build();
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
