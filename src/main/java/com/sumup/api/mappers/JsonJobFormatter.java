package com.sumup.api.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumup.api.dto.TaskDTO;
import com.sumup.api.exceptions.InvalidInputException;

import java.util.List;

public class JsonJobFormatter implements JobFormatter{
    @Override
    public String format(List<TaskDTO> tasks) {
        try {
            return new ObjectMapper().writeValueAsString(tasks);
        } catch (JsonProcessingException e) {
            throw new InvalidInputException();
        }
    }
}
