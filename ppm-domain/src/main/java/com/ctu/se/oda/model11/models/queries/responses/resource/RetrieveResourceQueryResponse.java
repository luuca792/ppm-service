package com.ctu.se.oda.model11.models.queries.responses.resource;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class RetrieveResourceQueryResponse {
    private Long resourceId;
    private String resourceName;
    private UUID taskId;

    public RetrieveResourceQueryResponse(Long resourceId, String resourceName, UUID taskId) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.taskId = taskId;
    }
}
