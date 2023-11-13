package com.ctu.se.oda.model11.models.commands.requests.material;

import com.ctu.se.oda.model11.enums.MaterialType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class UpdateMaterialCommandRequest {
    private UUID materialId;
    @Size(max = 250)
    private String materialName;
    private MaterialType materialTypeName;

    public UpdateMaterialCommandRequest(UUID materialId, String materialName, MaterialType materialTypeName) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialTypeName = materialTypeName;
    }

    @Override
    public String toString() {
        return "UpdateMaterialCommandRequest{" +
                "materialId" + this.materialId +
                "materialName" + this.materialName +
                "materialTypeName"+ this.materialTypeName +"}";
    }
}
