package com.ctu.se.oda.model11.mappers.subTask;

import com.ctu.se.oda.model11.entities.SubTask;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.subTask.UpdateSubTaskCommandRequest;
import com.ctu.se.oda.model11.repositories.ISubTaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor
public class UpdateSubTaskEntityMapper implements IInfrastructureMapper<UpdateSubTaskCommandRequest, SubTask> {

    @Autowired
    private ISubTaskRepository subTaskRepository;

    @Override
    public SubTask convert(UpdateSubTaskCommandRequest source) {
        Optional<SubTask> subTask = subTaskRepository.findById(source.getSubTaskId());
        if (!subTask.isPresent()) {
            throw new InternalServerErrorException(CustomErrorMessage.SUBTASK_ID_NOT_FOUND);
        }
        return SubTask.builder()
                .id(source.getSubTaskId())
                .name(source.getSubTaskName())
                .description(source.getSubTaskDescription())
                .isDone(source.getIsDone())
                .build();
    }
}
