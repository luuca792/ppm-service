package com.ctu.se.oda.model11.mappers.task;

import com.ctu.se.oda.model11.enums.TaskStatus;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.task.UpdateTaskRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UpdateTaskRequestMapper implements IMainMapper<UpdateTaskRequest, UpdateTaskCommandRequest> {
    @Override
    public UpdateTaskCommandRequest convert(UpdateTaskRequest source) {
        return UpdateTaskCommandRequest.builder()
                .taskName(source.getTaskName())
                .taskDescription(source.getTaskDescription())
                .taskStartAt(source.getTaskStartAt())
                .taskEndAt(source.getTaskEndAt())
                .taskStatus(source.getTaskStatus())
                .build();
    }
    @Override
    public UpdateTaskRequest reverse(UpdateTaskCommandRequest destination) {
        return UpdateTaskRequest.builder()
                .taskId(destination.getTaskId().toString())
                .taskName(destination.getTaskName())
                .taskDescription(destination.getTaskDescription())
                .taskStartAt(destination.getTaskStartAt())
                .taskEndAt(destination.getTaskEndAt())
                .taskStatus(destination.getTaskStatus())
                .build();
    }
}
