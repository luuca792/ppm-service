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
public class CreateMaterialCommandRequest {
    @NotBlank
    @Size(max = 250)
    private String materialName;
    @NotNull
    @Positive
    private Long materialType;
    public CreateMaterialCommandRequest(String materialName, Long materialType) {
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
