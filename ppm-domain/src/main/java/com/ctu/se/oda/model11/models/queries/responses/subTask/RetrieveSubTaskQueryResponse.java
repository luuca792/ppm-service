package com.ctu.se.oda.model11.models.queries.responses.subTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class RetrieveSubTaskQueryResponse {
    private UUID subTaskId;
    private String subTaskName;
    private UUID taskParentId;
}
