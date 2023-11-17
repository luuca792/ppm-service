package com.ctu.se.oda.model11.models.commands.requests.project;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class CreateProjectCommandRequest {
    @NotBlank
    @Size(max = 250)
    private String projectName;
    @NotNull
    @Positive
    private Double projectDuration;
    @NotNull
    private UUID projectCreatorId;

    public CreateProjectCommandRequest(String projectName, Double projectDuration, UUID projectCreatorId) {
        this.projectName = projectName;
        this.projectDuration = projectDuration;
        this.projectCreatorId = projectCreatorId;
    }

    @Override
    public String toString() {
        return "CreateProjectCommandRequest{" +
                "projectName=" + this.projectName +
                ", projectDuration=" + this.projectDuration +
                ", projectCreatorId=" + this.projectCreatorId +
                '}';
    }

}
