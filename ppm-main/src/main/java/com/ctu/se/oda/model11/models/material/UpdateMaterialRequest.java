package com.ctu.se.oda.model11.models.material;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateMaterialRequest {
    private UUID materialId;
    private String materialName;
    private String materialTypeName;
}
