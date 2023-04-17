package com.sumup.api.mappers;

import com.sumup.api.dto.TaskDTO;

import java.util.List;

public interface JobFormatter {
    String format(List<TaskDTO> tasks);

    static JobFormatter fromType(OutputFormat format) {
        switch (format) {
            case SCRIPT:
                return new ScriptJobFormatter();
            case JSON:
                return new JsonJobFormatter();
        }

        throw new IllegalArgumentException("Unknown format type");
    }
}
