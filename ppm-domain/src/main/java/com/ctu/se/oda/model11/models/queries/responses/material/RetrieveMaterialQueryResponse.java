package com.ctu.se.oda.model11.models.queries.responses.material;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class RetrieveMaterialQueryResponse {
    private Long materialId;
    private String materialName;
    private Long materialType;

    public RetrieveMaterialQueryResponse(Long materialId, String materialName, Long materialType) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialType = materialType;
    }
}
