package com.ctu.se.oda.model11.mappers.subTask;

import com.ctu.se.oda.model11.entities.SubTask;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.subTask.CreateSubTaskCommandRequest;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor
public class CreateSubTaskEntityMapper implements IInfrastructureMapper<CreateSubTaskCommandRequest, SubTask> {

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public SubTask convert(CreateSubTaskCommandRequest source) {
        Optional<Task> taskParent = taskRepository.findById(source.getTaskParentId());
        if (!taskParent.isPresent()) {
            throw new InternalServerErrorException(CustomErrorMessage.TASK_ID_NOT_FOUND);
        }
        return SubTask.builder()
                .name(source.getSubTaskName())
                .taskParentId(taskParent.get())
                .build();
    }
}
