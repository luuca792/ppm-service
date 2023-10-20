package com.ctu.se.oda.model11.models.resource;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UpdateResourceRequest {
    private Long resourceId;
    private String resourceName;
    private String taskId;
}
