package com.ctu.se.oda.model11.models.commands.requests.material;

import com.ctu.se.oda.model11.enums.MaterialType;
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
    @Size(max = 250)
    private String materialName;
    private MaterialType materialTypeName;
    public CreateMaterialCommandRequest(String materialName, MaterialType materialTypeName) {
        this.materialName = materialName;
        this.materialTypeName = materialTypeName;
    }

    @Override
    public String toString() {
        return "CreateMaterialCommandRequest{" +
                "materialName" + this.materialName +
                "materialTypeName"+ this.materialTypeName +"}";
    }
}
