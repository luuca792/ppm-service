package com.ctu.se.oda.model11.models.commands.requests.material;

import java.util.UUID;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class CreateMaterialCommandRequest {
    @Size(max = 250)
    private String materialName;
    private UUID materialType;
    public CreateMaterialCommandRequest(String materialName, UUID materialType) {
        this.materialName = materialName;
        this.materialType = materialType;
    }

    @Override
    public String toString() {
        return "CreateMaterialCommandRequest{" +
                "materialName" + this.materialName +
                "materialType"+ this.materialType +"}";
    }
}
