package com.ctu.se.oda.model11.models.commands.responses.project;

import com.ctu.se.oda.model11.models.IDomainModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateProjectCommandResponse implements IDomainModel{
    private String projectId;
    private String projectName;
    private Double projectDuration;
    private String projectCreatorId;
    public CreateProjectCommandResponse(String projectId, String projectName, Double projectDuration, String projectCreatorId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDuration = projectDuration;
        this.projectCreatorId = projectCreatorId;
    }

}
