package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.Resource;
import com.ctu.se.oda.model11.entities.ResourceMaterial;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;
import com.ctu.se.oda.model11.repositories.*;

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

    @Autowired
    private IResourceRepository resourceRepository;

    @Autowired
    private IMaterialRepository materialRepository;

    @Autowired
    private IResourceMaterialRepository resourceMaterialRepository;

    @Override
    public CreateTaskCommandResponse createTask(@Valid CreateTaskCommandRequest createTaskCommandRequest) {
        var retrievedProjectId = projectRepository.findById(createTaskCommandRequest.getProjectId());
        if (retrievedProjectId.isEmpty()) {
            throw new InternalServerErrorException(CustomErrorMessage.PROJECT_ID_DO_NOT_EXIST);
        }
        if (Objects.nonNull(createTaskCommandRequest.getTaskParentId())) {
            var retrievedTaskParentId = taskRepository.findById(createTaskCommandRequest.getTaskParentId());
            if (retrievedTaskParentId.isEmpty()) {
                throw new InternalServerErrorException(CustomErrorMessage.PARENT_TASK_ID_DO_NOT_EXIST);
            }
        }
        var retrieveTask = taskRepository.save(createTaskEntityMapper.convert(createTaskCommandRequest));

        Resource resource = new Resource();
        var createdResource = resourceRepository.save(resource);
        retrieveTask.setResource(createdResource);

        return createTaskEntityMapper.reverse(taskRepository.save(retrieveTask));
    }
    
    @Override
    public UpdateTaskCommandResponse updateTask(@Valid UpdateTaskCommandRequest updateTaskCommandRequest) {
        var retrievedTask = taskRepository.findById(updateTaskCommandRequest.getTaskId());
        if (retrievedTask.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.TASK_ID_DO_NOT_EXIST);
        }
        var mappedTask = updateTaskEntityMapper.convert(updateTaskCommandRequest);
        var creatingTask = retrievedTask.get();
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
<<<<<<< HEAD
        if (Objects.nonNull(mappedTask.getDuration())) {
            creatingTask.setDuration(mappedTask.getDuration());
        }
        return updateTaskEntityMapper.reverse(
                taskRepository.save(creatingTask)
=======
        if (Objects.nonNull(mappedTask.getStatus())) {
            creatingTask.setStatus(mappedTask.getStatus());
        }
        return updateTaskEntityMapper.reverse(taskRepository.save(retrievedTask.get())
>>>>>>> main
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
<<<<<<< HEAD
                                    .taskDuration(subtask.getDuration())
=======
                                    .taskStatus(subtask.getStatus())
>>>>>>> main
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
                            .taskStatus(task.getStatus())
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
            throw new InternalServerErrorException(CustomErrorMessage.NOT_FOUND_BY_ID);
        }
        var retrievedTask = retrievedTaskOptional.get();

        List<RetrieveTaskQueryResponse> subtasks = getSubtasksRecursively(retrievedTask);

        return RetrieveTaskQueryResponse.builder()
                .taskId(retrievedTask.getId())
                .taskName(retrievedTask.getName())
                .taskDescription(retrievedTask.getDescription())
                .taskStartAt(retrievedTask.getStartAt())
                .taskEndAt(retrievedTask.getEndAt())
<<<<<<< HEAD
                .taskDuration(retrievedTask.getDuration())
=======
                .taskStatus(retrievedTask.getStatus())
>>>>>>> main
                .projectId(retrievedTask.getProjectId())
                .taskParentId(retrievedTask.getTaskParent() != null ? retrievedTask.getTaskParent().getId() : null)
                .subtasks(subtasks)
                .build();
    }

    @Override
    public void addMaterialToTask(UUID taskId, UUID materialId, Double amount) {
        var retrieveTask = taskRepository.findById(taskId);
        if(retrieveTask.isEmpty()) {
            throw new InternalServerErrorException(CustomErrorMessage.TASK_ID_DO_NOT_EXIST);
        }

        var retrieveResource = retrieveTask.get().getResource();
        ResourceMaterial resourceMaterial = new ResourceMaterial();
        resourceMaterial.setResource(retrieveResource);

        var materialRetrieveOptional = materialRepository.findById(materialId);
        if(materialRetrieveOptional.isEmpty()) {
            throw new InternalServerErrorException(CustomErrorMessage.MATERIAL_ID_DO_NOT_EXIST);
        }

        var materialRetrieve = materialRetrieveOptional.get();
        resourceMaterial.setMaterial(materialRetrieve);
        resourceMaterial.setAmount(amount);

        resourceMaterialRepository.save(resourceMaterial);
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
