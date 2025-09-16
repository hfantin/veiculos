package com.github.hfantin.veiculos.domain.entity;

public class AppInfo {
    private String name;
    private String version;
    private String description;

    public AppInfo(String name, String version, String description) {
        this.name = name;
        this.version = version;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }
}
