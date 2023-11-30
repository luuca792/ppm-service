package com.ctu.se.oda.model11.models.commands.requests.materialType;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateMaterialTypeCommandRequest {

    private UUID materialTypeId;
    @NotNull
    private String materialTypeName;

    @Override
    public String toString() {
        return "UpdateMaterialTypeCommandRequest{" +
                "materialTypeId" + this.materialTypeId +
                "materialType"+ this.materialTypeName +"}";
    }
}
