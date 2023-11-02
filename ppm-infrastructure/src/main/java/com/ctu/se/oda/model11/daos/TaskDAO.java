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

import java.util.Collections;
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
        var retrievedProjectId = projectRepository.findById(createTaskCommandRequest.getProjectId());
        if (retrievedProjectId.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.PROJECT_ID_DO_NOT_EXIST);
        }
        if (Objects.nonNull(createTaskCommandRequest.getTaskParentId())) {
            var retrievedTaskParentId = taskRepository.findById(createTaskCommandRequest.getTaskParentId());
            if (retrievedTaskParentId.isEmpty()) {
                throw new IllegalArgumentException(CustomErrorMessage.PARENT_TASK_ID_DO_NOT_EXIST);
            }
        }
        return this.createTaskEntityMapper.reverse(taskRepository.save(createTaskEntityMapper
                .convert(createTaskCommandRequest))
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
                throw new IllegalArgumentException(CustomErrorMessage.START_DATE_AFTER_END_DATE);
            }
        }
        if (Objects.nonNull(mappedTask.getStartAt())) {
            creatingTask.setStartAt(mappedTask.getStartAt());
        }
        if (Objects.nonNull(mappedTask.getEndAt())) {
            creatingTask.setEndAt(mappedTask.getEndAt());
        }
        if (Objects.nonNull(mappedTask.getDuration())) {
            creatingTask.setDuration(mappedTask.getDuration());
        }
        return updateTaskEntityMapper.reverse(
                taskRepository.save(creatingTask)
        );
    }

    @Override
    public List<RetrieveTaskQueryResponse> listTask() {
        return this.taskRepository.findAll().stream().map(
                task -> {
                    List<RetrieveTaskQueryResponse> subtasks = task.getSubtasks().stream().map(
                            subtask -> RetrieveTaskQueryResponse.builder()
                                    .taskId(subtask.getId())
                                    .taskName(subtask.getName())
                                    .taskDescription(subtask.getDescription())
                                    .taskStartAt(subtask.getStartAt())
                                    .taskEndAt(subtask.getEndAt())
                                    .taskDuration(subtask.getDuration())
                                    .projectId(subtask.getProjectId())
                                    .taskParentId(subtask.getTaskParent().getId())
                                    .build()
                    ).collect(Collectors.toList());
                    return RetrieveTaskQueryResponse.builder()
                            .taskId(task.getId())
                            .taskName(task.getName())
                            .taskDescription(task.getDescription())
                            .taskStartAt(task.getStartAt())
                            .taskEndAt(task.getEndAt())
                            .taskDuration(task.getDuration())
                            .projectId(task.getProjectId())
                            .taskParentId(task.getTaskParent() != null ? task.getTaskParent().getId() : null)
                            .subtasks(subtasks)
                            .build();
                }
        ).collect(Collectors.toList());
    }

    @Override
    public RetrieveTaskQueryResponse detailTask(UUID taskId) {
        var retrievedTaskOptional = taskRepository.findById(taskId);
        if (retrievedTaskOptional.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.NOT_FOUND_BY_ID);
        }
        var retrievedTask = retrievedTaskOptional.get();

        List<RetrieveTaskQueryResponse> subtasks = getSubtasksRecursively(retrievedTask);

        return RetrieveTaskQueryResponse.builder()
                .taskId(retrievedTask.getId())
                .taskName(retrievedTask.getName())
                .taskDescription(retrievedTask.getDescription())
                .taskStartAt(retrievedTask.getStartAt())
                .taskEndAt(retrievedTask.getEndAt())
                .taskDuration(retrievedTask.getDuration())
                .projectId(retrievedTask.getProjectId())
                .taskParentId(retrievedTask.getTaskParent() != null ? retrievedTask.getTaskParent().getId() : null)
                .subtasks(subtasks)
                .build();
    }

    private List<RetrieveTaskQueryResponse> getSubtasksRecursively(Task task) {
        if (task.getSubtasks().isEmpty()) {
            return Collections.emptyList();
        }
        return task.getSubtasks().stream().map(subtask -> {
            List<RetrieveTaskQueryResponse> subtasksOfSubtask = getSubtasksRecursively(subtask);

            return RetrieveTaskQueryResponse.builder()
                    .taskId(subtask.getId())
                    .taskName(subtask.getName())
                    .taskDescription(subtask.getDescription())
                    .taskStartAt(subtask.getStartAt())
                    .taskEndAt(subtask.getEndAt())
                    .projectId(subtask.getProjectId())
                    .taskParentId(subtask.getTaskParent() != null ? subtask.getTaskParent().getId() : null)
                    .subtasks(subtasksOfSubtask)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
