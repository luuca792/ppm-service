package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.SubTask;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.subTask.CreateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.subTask.UpdateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.subTask.RetrieveSubTaskQueryResponse;
import com.ctu.se.oda.model11.repositories.ISubTaskRepository;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import com.ctu.se.oda.model11.utils.ModelMapperUtil;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Validated
public class SubTaskDAO implements ISubTaskService{

    @Autowired
    private ISubTaskRepository subTaskRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IInfrastructureMapper<CreateSubTaskCommandRequest, SubTask> createSubTaskEntityMapper;

    @Autowired
    private IInfrastructureMapper<UpdateSubTaskCommandRequest, SubTask> updateSubTaskEntityMapper;

    @Override
    public List<RetrieveSubTaskQueryResponse> getAllSubTaskOfTask(UUID id) {
        Optional<Task> TaskParent = taskRepository.findById(id);
        if (!TaskParent.isPresent()) {
            throw new InternalServerErrorException(CustomErrorMessage.TASK_ID_NOT_FOUND);
        }
        List<SubTask> subtasks = subTaskRepository.findAllByTaskParentId(TaskParent.get());
        return subtasks.stream().map(subtask -> RetrieveSubTaskQueryResponse.builder()
                .subTaskId(subtask.getId())
                .subTaskName(subtask.getName())
                .taskParentId(subtask.getTaskParentId().getId())
                .build()).collect(Collectors.toList());
    }

    @Override
    public RetrieveSubTaskQueryResponse getSubTaskById(UUID id) {
        Optional<SubTask> retrievedSubTask = subTaskRepository.findById(id);
        if (!retrievedSubTask.isPresent()) {
            throw new InternalServerErrorException(CustomErrorMessage.SUBTASK_ID_NOT_FOUND);
        }
        SubTask subtask = retrievedSubTask.get();
        return RetrieveSubTaskQueryResponse.builder()
                .subTaskId(subtask.getId())
                .subTaskName(subtask.getName())
                .taskParentId(subtask.getTaskParentId().getId())
                .build();
    }

    @Override
    public void createSubTask(CreateSubTaskCommandRequest request) {
        Optional<Task> TaskParent = taskRepository.findById(request.getTaskParentId());
        if (!TaskParent.isPresent()) {
            throw new InternalServerErrorException(CustomErrorMessage.TASK_ID_NOT_FOUND);
        }
        subTaskRepository.save(createSubTaskEntityMapper.convert(request));
    }

    @Override
    public void updateSubTask(UpdateSubTaskCommandRequest request) {
        Optional<SubTask> retrievedSubTask = subTaskRepository.findById(request.getSubTaskId());
        if (!retrievedSubTask.isPresent()) {
            throw new InternalServerErrorException(CustomErrorMessage.SUBTASK_ID_NOT_FOUND);
        }
        SubTask updatedSubTask = updateSubTaskEntityMapper.convert(request);
        ModelMapperUtil.copy(updatedSubTask, retrievedSubTask.get());
        subTaskRepository.save(retrievedSubTask.get());
    }

    @Override
    public void deleteSubTaskById(UUID id) {
        Optional<SubTask> retrievedSubTask = subTaskRepository.findById(id);
        if (!retrievedSubTask.isPresent()) {
            throw new InternalServerErrorException(CustomErrorMessage.SUBTASK_ID_NOT_FOUND);
        }
        subTaskRepository.deleteById(id);
    }
}
