package com.ctu.se.oda.model11.models.materialType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateMaterialTypeRequest {
    private String materialTypeId;
    private String materialTypeName;
}
