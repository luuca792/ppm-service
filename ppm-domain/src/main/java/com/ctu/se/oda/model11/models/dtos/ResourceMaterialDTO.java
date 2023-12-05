package com.ctu.se.oda.model11.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceMaterialDTO {
    private UUID id;
    private UUID resourceId;
    private UUID materialId;
    private Double amount;
}
