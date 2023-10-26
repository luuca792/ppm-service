package com.ctu.se.oda.model11.mappers.task;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.project.UpdateProjectRequest;
import com.ctu.se.oda.model11.models.task.UpdateTaskRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class UpdateTaskRequestMapper implements IMainMapper<UpdateTaskRequest, UpdateTaskCommandRequest> {
    @Override
    public UpdateTaskCommandRequest convert(UpdateTaskRequest source) {
        List<UpdateTaskCommandRequest> convertedSubtasks = null;
        if (source.getSubtasks() != null) {
            convertedSubtasks = source.getSubtasks().stream()
                    .map(this::convert)
                    .collect(Collectors.toList());
        }
        return UpdateTaskCommandRequest.builder()
                .taskName(source.getTaskName())
                .taskDescription(source.getTaskDescription())
                .taskStartAt(source.getTaskStartAt())
                .taskEndAt(source.getTaskEndAt())
                .projectId(UUID.fromString(source.getProjectId()))
                .taskParentId(source.getTaskParentId())
                .subtasks(convertedSubtasks)
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
                .projectId(destination.getProjectId().toString())
                .build();
    }
}
