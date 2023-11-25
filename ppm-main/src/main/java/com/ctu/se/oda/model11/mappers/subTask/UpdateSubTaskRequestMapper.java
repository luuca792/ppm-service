package com.ctu.se.oda.model11.mappers.subTask;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.subTask.UpdateSubTaskCommandRequest;
import com.ctu.se.oda.model11.models.subTask.UpdateSubTaskRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class UpdateSubTaskRequestMapper implements IMainMapper<UpdateSubTaskRequest, UpdateSubTaskCommandRequest> {
    @Override
    public UpdateSubTaskCommandRequest convert(UpdateSubTaskRequest source) {
        return UpdateSubTaskCommandRequest.builder()
                .subTaskId(UUID.fromString(source.getSubTaskId()))
                .subTaskName(source.getSubTaskName())
                .build();
    }
}
