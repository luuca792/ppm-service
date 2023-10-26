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
        var retrieveProjectId = this.projectRepository.findById(createTaskCommandRequest.getProjectId());
        if(retrieveProjectId.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.PROJECT_ID_DO_NOT_EXIST);
        }

        return this.createTaskEntityMapper.reverse(
                this.taskRepository.save(this.createTaskEntityMapper.convert(createTaskCommandRequest))
        );
    }
    @Override
    public UpdateTaskCommandResponse updateTask(@Valid UpdateTaskCommandRequest updateTaskCommandRequest, UUID taskId) {
        updateTaskCommandRequest.setTaskId(taskId);
        return this.updateTaskEntityMapper.reverse(
                this.taskRepository.save(this.updateTaskEntityMapper.convert(updateTaskCommandRequest))
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
                            .projectId(task.getProjectId())
                            .taskParentId(task.getTaskParent() != null ? task.getTaskParent().getId() : null)
                            .subtasks(subtasks)
                            .build();
                }
        ).collect(Collectors.toList());
    }
    @Override
    public RetrieveTaskQueryResponse detailTask(UUID taskId) {
        var retrievedTaskOptional = this.taskRepository.findById(taskId);
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
            List<RetrieveTaskQueryResponse> subtasksOfSubtask = getSubtasksRecursively(subtask); // Đệ quy

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
    public CreateTaskCommandResponse addSubTaskToTask(@Valid CreateTaskCommandRequest createTaskCommandRequest, UUID parentTaskId) {
        var parentTask = this.taskRepository.findById(parentTaskId).orElseThrow(
                () -> new IllegalArgumentException(CustomErrorMessage.NOT_FOUND_BY_ID)
        );

        var subTask = this.createTaskEntityMapper.convert(createTaskCommandRequest);
        subTask.setTaskParent(parentTask);

        parentTask.getSubtasks().add(subTask);

        this.taskRepository.save(parentTask);

        return this.createTaskEntityMapper.reverse(subTask);
    }
    @Override
    public void deleteTask(UUID taskId) {
        this.taskRepository.deleteById(taskId);
    }
}
