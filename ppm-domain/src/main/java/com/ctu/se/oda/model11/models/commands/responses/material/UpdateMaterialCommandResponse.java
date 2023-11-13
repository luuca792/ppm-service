package com.ctu.se.oda.model11.models.commands.responses.material;

import com.ctu.se.oda.model11.enums.MaterialType;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class UpdateMaterialCommandResponse {
    private UUID materialId;
    private String materialName;
    private MaterialType materialTypeName;

    public UpdateMaterialCommandResponse(UUID materialId, String materialName, MaterialType materialTypeName) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialTypeName = materialTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateMaterialCommandResponse that = (UpdateMaterialCommandResponse) o;
        return  materialId.equals(that.materialId) &&
                materialName.equals(that.materialName) &&
                materialTypeName.equals(that.materialTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, materialName, materialTypeName);
    }
}
