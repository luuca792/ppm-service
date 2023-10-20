package com.ctu.se.oda.model11.models.commands.requests.material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class UpdateMaterialCommandRequest {
    private Long materialId;
    @NotBlank
    @Size(max = 250)
    private String materialName;
    @NotNull
    @Positive
    private Long materialType;
    public UpdateMaterialCommandRequest(Long materialId,String materialName, Long materialType) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialType = materialType;
    }

    @Override
    public String toString() {
        return "CreateMaterialCommandRequest{" +
                "materialId" + this.materialId +
                "materialName" + this.materialName +
                "materialType"+ this.materialType +"}";
    }
}
