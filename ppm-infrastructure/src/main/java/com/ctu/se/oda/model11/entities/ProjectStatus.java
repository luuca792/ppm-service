package com.ctu.se.oda.model11.entities;

import com.ctu.se.oda.model11.daos.IProjectStatusService;


public enum ProjectStatus implements IProjectStatusService{
    PENDING(1),
    IN_PROGRESS(2),
    ON_HOLD(3),
    DONE(4);
    private int value;

    ProjectStatus(int value) {
        value = value;
    }

    public int getValueStatusProject() {
        return value;
    }

    public void setValueStatusProject(int value) {
        value = value;
    }

    public static ProjectStatus fromValue(int value){
        for (ProjectStatus projectStatus : ProjectStatus.values()) {
            if (projectStatus.getValueStatusProject() == value) {
                return projectStatus;
            }
        }
        throw new IllegalArgumentException("Đầu vào trạng thái không hợp lệ"+ value);
    }
}
