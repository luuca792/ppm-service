package com.ctu.se.oda.model11.models.commands.requests.material;

import java.util.UUID;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateMaterialCommandRequest {
    private UUID materialId;
    @Size(max = 250)
    private String materialName;
    private UUID materialType;

    @Override
    public String toString() {
        return "UpdateMaterialCommandRequest{" +
                "materialId" + this.materialId +
                "materialName" + this.materialName +
                "materialType"+ this.materialType +"}";
    }
}
