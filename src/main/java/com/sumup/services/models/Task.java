package com.sumup.services.models;

import java.util.List;

public class Task {
    private final String name;
    private final String command;
    private final List<String> dependencies;

    public Task(String name, String command, List<String> dependencies) {
        this.name = name;
        this.command = command;
        this.dependencies = dependencies;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getDependencies() {
        return dependencies;
    }
}
