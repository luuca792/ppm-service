package com.ctu.se.oda.model11.models.commands.responses.resource;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateResourceCommandResponse {
    private Long resourceId;
    private String resourceName;
    private UUID taskId;

    public CreateResourceCommandResponse(Long resourceId, String resourceName, UUID taskId) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateResourceCommandResponse that = (CreateResourceCommandResponse) o;
        return resourceName.equals(that.resourceName) && taskId.equals(that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceName, taskId);
    }
}
