package com.ctu.se.oda.model11.models.commands.requests.project;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateProjectCommandRequest {
    private String projectName;
    private Double projectDuration;
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
