package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.CreateTaskCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import jakarta.validation.Valid;
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
public class TaskDAO implements ITaskService{
    @Autowired
    private IInfrastructureMapper<CreateTaskCommandRequest, Task, CreateTaskCommandResponse> createTaskEntityMapper;
    @Autowired
    private IInfrastructureMapper<UpdateTaskCommandRequest, Task, UpdateTaskCommandResponse> updateTaskEntityMapper;
    @Autowired
    private ITaskRepository taskRepository;
    @Override
    public CreateTaskCommandResponse createTask(@Valid CreateTaskCommandRequest createTaskCommandRequest) {
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
                task -> RetrieveTaskQueryResponse.builder()
                        .taskId(task.getId())
                        .taskName(task.getName())
                        .taskDescription(task.getDescription())
                        .taskStartAt(task.getStartAt())
                        .taskEndAt(task.getEndAt())
                        .build()
        ).collect(Collectors.toList());
    }
    @Override
    public RetrieveTaskQueryResponse detailTask(UUID taskId) {
        var foundTask = this.taskRepository.findById(taskId).get();
        return RetrieveTaskQueryResponse.builder()
                .taskId(foundTask.getId())
                .taskName(foundTask.getName())
                .taskDescription(foundTask.getDescription())
                .taskStartAt(foundTask.getStartAt())
                .taskEndAt(foundTask.getEndAt())
                .build();
    }

    @Override
    public void deleteTask(UUID taskId) {
        this.taskRepository.deleteById(taskId);
    }
}