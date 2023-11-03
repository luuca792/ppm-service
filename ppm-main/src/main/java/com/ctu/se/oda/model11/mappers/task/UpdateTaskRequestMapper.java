package com.ctu.se.oda.model11.mappers.task;

import com.ctu.se.oda.model11.enums.TaskStatus;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.task.UpdateTaskRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class UpdateTaskRequestMapper implements IMainMapper<UpdateTaskRequest, UpdateTaskCommandRequest> {

    @Override
    public UpdateTaskCommandRequest convert(UpdateTaskRequest source) {
        return UpdateTaskCommandRequest.builder()
                .taskId(UUID.fromString(source.getTaskId()))
                .taskName(Optional.ofNullable(source.getTaskName()).orElse(null))
                .taskDescription(source.getTaskDescription())
                .taskStartAt(source.getTaskStartAt())
                .taskEndAt(source.getTaskEndAt())
<<<<<<< HEAD
                .taskDuration(source.getTaskDuration())
                .projectId(UUID.fromString(source.getProjectId()))
                .taskParentId(UUID.fromString(source.getTaskParentId()))
=======
                .taskStatus(Optional.ofNullable(source.getTaskStatus()).map(TaskStatus::valueOf).orElse(null))
                .projectId(Optional.ofNullable(source.getProjectId()).map(UUID::fromString).orElse(null))
                .taskParentId(Optional.ofNullable(source.getTaskParentId()).map(UUID::fromString).orElse(null))
>>>>>>> main
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
<<<<<<< HEAD
                .taskDuration(destination.getTaskDuration())
=======
                .taskStatus(destination.getTaskStatus().toString())
>>>>>>> main
                .projectId(destination.getProjectId().toString())
                .build();
    }
}
