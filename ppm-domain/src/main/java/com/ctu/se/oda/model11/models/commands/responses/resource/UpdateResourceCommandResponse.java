package com.ctu.se.oda.model11.models.commands.responses.resource;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class UpdateResourceCommandResponse {
    private Long resourceId;
    private String resourceName;
    private UUID taskId;

    public UpdateResourceCommandResponse(Long resourceId, String resourceName, UUID taskId) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.taskId = taskId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateResourceCommandResponse that = (UpdateResourceCommandResponse) o;
        return Objects.equals(resourceId, that.resourceId)
                && Objects.equals(resourceName, that.resourceName)
                && Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, resourceName, taskId);
    }
}
