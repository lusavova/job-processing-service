package com.sumup.api.mappers;

import com.sumup.api.dto.TaskDTO;
import com.sumup.services.models.Task;

public class TaskMapper {

    public static Task from(TaskDTO taskDTO) {
        return new Task(taskDTO.getName(), taskDTO.getCommand(), taskDTO.getRequires());
    }

    public static TaskDTO to(Task task) {
        return new TaskDTO(task.getName(), task.getCommand(), task.getDependencies());
    }
}
