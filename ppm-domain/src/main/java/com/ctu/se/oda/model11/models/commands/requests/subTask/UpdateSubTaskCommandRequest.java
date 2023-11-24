package com.ctu.se.oda.model11.models.commands.requests.subTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UpdateSubTaskCommandRequest {
    private UUID subTaskId;
    private String subTaskName;
}
