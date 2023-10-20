package com.ctu.se.oda.model11.models.commands.responses.material;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Data
@Builder
@RequiredArgsConstructor
public class UpdateMaterialCommandResponse {
    private Long materialId;
    private String materialName;
    private Long materialType;

    public UpdateMaterialCommandResponse(Long materialId, String materialName, Long materialType) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialType = materialType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateMaterialCommandResponse that = (UpdateMaterialCommandResponse) o;
        return  materialId.equals(that.materialId) &&
                materialName.equals(that.materialName) &&
                materialType.equals(that.materialType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, materialName, materialType);
    }
}
