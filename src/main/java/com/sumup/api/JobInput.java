package com.sumup.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sumup.api.dto.TaskDTO;

import java.util.List;

public class JobInput {
    private final List<TaskDTO> tasks;

    @JsonCreator
    public JobInput(@JsonProperty(value = "tasks", required = true) List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }
}
