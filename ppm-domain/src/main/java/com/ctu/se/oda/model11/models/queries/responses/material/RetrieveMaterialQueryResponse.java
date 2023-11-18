package com.ctu.se.oda.model11.models.queries.responses.material;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class RetrieveMaterialQueryResponse {
    private UUID materialId;
    private String materialName;
    private UUID materialTypeName;

    public RetrieveMaterialQueryResponse(UUID materialId, String materialName, UUID materialTypeName) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialTypeName = materialTypeName;
    }
}
