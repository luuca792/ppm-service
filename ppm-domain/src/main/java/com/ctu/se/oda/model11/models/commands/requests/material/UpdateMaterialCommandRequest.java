package com.ctu.se.oda.model11.models.commands.requests.material;

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

    @Positive
    private Long materialType;

    public UpdateMaterialCommandRequest(UUID materialId, String materialName, Long materialType) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialType = materialType;
    }

    @Override
    public String toString() {
        return "UpdateMaterialCommandRequest{" +
                "materialId" + this.materialId +
                "materialName" + this.materialName +
                "materialType"+ this.materialType +"}";
    }
}
