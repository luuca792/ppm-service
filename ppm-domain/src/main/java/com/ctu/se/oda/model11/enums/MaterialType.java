package com.ctu.se.oda.model11.enums;

import java.util.Arrays;

public enum MaterialType {
    PHAN_BON(0L),
    NGUYEN_LIEU(1L);

    private Long materialTypeId;

    MaterialType(Long materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public Long getMaterialType() {
        return materialTypeId;
    }

    public static MaterialType fromValue(Long materialTypeId){
        return Arrays.stream(MaterialType.values())
                .filter(materialType -> materialType.getMaterialType() == materialTypeId)
                .findFirst()
                .orElse(null);
    }
}
