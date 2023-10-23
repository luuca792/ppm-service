package com.ctu.se.oda.model11.models.material;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMaterialRequest {
    private String materialName;
    private Long materialType;
}
