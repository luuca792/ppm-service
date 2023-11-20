package com.ctu.se.oda.model11.models.queries.responses.material;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RetrieveMaterialQueryResponse {
    private UUID materialId;
    private String materialName;
    private UUID materialTypeName;
}
