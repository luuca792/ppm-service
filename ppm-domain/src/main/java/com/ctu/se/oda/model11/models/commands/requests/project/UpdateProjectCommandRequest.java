package com.ctu.se.oda.model11.models.commands.requests.project;

import java.time.LocalDate;
import java.util.UUID;

import com.ctu.se.oda.model11.enums.ProjectStatus;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateProjectCommandRequest {
	
    private UUID projectId;
    
    @Size(max = 250)
    private String projectName;
    
    private LocalDate projectStartAt;
    
    private LocalDate projectEndAt;
    @Positive
    private Double projectDuration;
    
    private ProjectStatus projectStatus;
    
    private UUID projectCreatorId;

    @Override
    public String toString() {
        return "UpdateProjectCommandRequest{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectStartAt=" + projectStartAt +
                ", projectEndAt=" + projectEndAt +
                ", projectDuration=" + projectDuration +
                ", projectStatus=" + projectStatus +
                ", projectCreatorId=" + projectCreatorId +
                '}';
    }
}
