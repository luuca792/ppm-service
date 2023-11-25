package com.ctu.se.oda.model11.models.subTask;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateSubTaskRequest {
    private String subTaskId;
    private String subTaskName;
}
