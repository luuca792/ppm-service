package com.ctu.se.oda.model11.enums;

import java.util.Arrays;

public enum MaterialType {
    FERTILIZER(0L),
    INGREDIENT(1L),
    SEEDS(2L),
    SEEDLING(3L),
    UTENSILS(4L),
    PLANTING_LAND(5L);

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
