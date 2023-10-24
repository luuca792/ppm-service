package com.ctu.se.oda.model11.entities;

import com.ctu.se.oda.model11.daos.ITaskStatusService;

public enum TaskStatus implements ITaskStatusService {
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
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.getValueStatus() == valueStatus) {
                return taskStatus;
            }
        }
        return null;
    }
}


