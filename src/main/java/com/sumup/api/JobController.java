package com.sumup.api;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.sumup.api.dto.TaskDTO;
import com.sumup.api.exceptions.InvalidInputException;
import com.sumup.api.mappers.*;

import com.sumup.services.models.Task;
import com.sumup.services.JobSortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class JobController {
    private final JobSortingService jobSortingService;

    @Autowired
    public JobController(JobSortingService jobSortingService) {
        this.jobSortingService = jobSortingService;
    }

    @PostMapping("/tasks/sort")
    public ResponseEntity<String> sortTasks(
            @RequestParam OutputFormat outputFormat,
            @RequestBody JobInput input) {
        List<Task> tasks = input.getTasks().stream()
                .map(TaskMapper::from)
                .collect(Collectors.toList());
        List<Task> sortedTasks = jobSortingService.sort(tasks);
        List<TaskDTO> tasksDTOs = sortedTasks.stream()
                .map(TaskMapper::to)
                .collect(Collectors.toList());
        return ResponseEntity.ok(JobFormatter.fromType(outputFormat).format(tasksDTOs));
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<String> handleJsonMappingException() {
        return ResponseEntity.badRequest().body("Invalid JSON input.");
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException() {
        return ResponseEntity.badRequest().body("Invalid input.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
