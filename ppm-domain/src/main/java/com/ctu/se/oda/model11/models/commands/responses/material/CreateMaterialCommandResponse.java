package com.ctu.se.oda.model11.models.commands.responses.material;

import com.ctu.se.oda.model11.enums.MaterialType;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateMaterialCommandResponse {
    private UUID materialId;
    private String materialName;
    private MaterialType materialType;

    public CreateMaterialCommandResponse(UUID materialId, String materialName, MaterialType materialType) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialType = materialType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMaterialCommandResponse that = (CreateMaterialCommandResponse) o;
        return materialName.equals(that.materialName) && materialType.equals(that.materialType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialName, materialType);
    }
}
