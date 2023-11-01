package com.ctu.se.oda.model11.models;

import lombok.Data;

@Data
public class AppInfo {

    private String name;
    private String version;

    public AppInfo(String name, String version) {
        this.name = name;
        this.version = version;
    }
}
