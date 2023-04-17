package com.sumup.api.mappers;

import com.sumup.api.dto.TaskDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ScriptJobFormatter implements JobFormatter{
    @Override
    public String format(List<TaskDTO> tasks) {
        return tasks.stream()
                .map(TaskDTO::getCommand)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
