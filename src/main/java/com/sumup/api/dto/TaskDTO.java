package com.sumup.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    private String name;

    private String command;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final List<String> requires;

    @JsonCreator
    public TaskDTO(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "command", required = true) String command,
            @JsonProperty(value = "requires") List<String> requires) {
        this.name = name;
        this.command = command;
        this.requires = requires != null ? requires : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getRequires() {
        return requires;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
