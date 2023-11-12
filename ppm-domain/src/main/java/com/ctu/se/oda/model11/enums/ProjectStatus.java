package com.ctu.se.oda.model11.enums;

import java.util.Arrays;

public enum ProjectStatus {

    PENDING(0),
    IN_PROGRESS(1),
    ON_HOLD(2),
    DONE(3);

    private int valueStatus;

    ProjectStatus(int valueStatus) {
        this.valueStatus = valueStatus;
    }

    public int getValueStatus() {
        return valueStatus;
    }

    public static ProjectStatus fromValue(int valueStatus){
        return Arrays.stream(ProjectStatus.values())
                .filter(projectStatus -> projectStatus.getValueStatus() == valueStatus)
                .findFirst()
                .orElse(null);
    }
}
