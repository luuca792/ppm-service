package com.ctu.se.oda.model11.models.commands.requests.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateResourceCommandRequest {
    @NotBlank
    @Size(max = 250)
    private String resourceName;
    @NotNull
    private UUID taskId;
    public CreateResourceCommandRequest(String resourceName, UUID taskId) {
        this.resourceName = resourceName;
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "CreateResourceCommandRequest{" +
                "resourceName" + this.resourceName +
                "taskId"+ this.taskId +
                "}";
    }
}
