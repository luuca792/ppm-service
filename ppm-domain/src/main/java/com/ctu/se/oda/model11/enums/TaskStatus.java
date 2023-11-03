package com.ctu.se.oda.model11.enums;

import java.util.Arrays;

public enum TaskStatus {
    OPEN(0),
    IN_PROGRESS(1),
    DONE(2),
    CLOSED(3);

    private int valueStatus;

    TaskStatus(int valueStatus) {
        this.valueStatus = valueStatus;
    }

    public int getValueStatus() {
        return valueStatus;
    }

    public static TaskStatus fromValue(int valueStatus){
        return Arrays.stream(TaskStatus.values())
            .filter(taskStatus -> taskStatus.getValueStatus() == valueStatus)
            .findFirst()
            .orElse(null);
    }
}


