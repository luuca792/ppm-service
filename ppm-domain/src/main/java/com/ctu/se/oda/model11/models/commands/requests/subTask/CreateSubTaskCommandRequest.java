package com.ctu.se.oda.model11.models.commands.requests.subTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CreateSubTaskCommandRequest {
    private String subTaskName;
    private String subTaskDescription;
    private UUID taskParentId;
}
