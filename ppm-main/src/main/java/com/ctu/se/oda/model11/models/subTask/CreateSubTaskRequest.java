package com.ctu.se.oda.model11.models.subTask;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSubTaskRequest {
    private String subTaskName;
    private String description;
    private String taskParentId;
}
