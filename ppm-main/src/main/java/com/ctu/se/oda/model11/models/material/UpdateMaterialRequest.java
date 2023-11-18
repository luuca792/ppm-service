package com.ctu.se.oda.model11.models.material;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateMaterialRequest {
    private String materialId;
    private String materialName;
    private String materialTypeName;
}
