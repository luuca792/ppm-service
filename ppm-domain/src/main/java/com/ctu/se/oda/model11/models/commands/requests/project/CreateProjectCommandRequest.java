package com.ctu.se.oda.model11.models.commands.requests.project;

import com.ctu.se.oda.model11.models.IDomainModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateProjectCommandRequest implements IDomainModel{
    private String projectName;
    private Double projectDuration;
    private String projectCreatorId;

    public static CreateProjectCommandRequest createInstance(String projectName, Double projectDuration, String projectCreatorId) {
        return new CreateProjectCommandRequest(projectName, projectDuration, projectCreatorId);
    }

    public CreateProjectCommandRequest(String projectName, Double projectDuration, String projectCreatorId) {
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
