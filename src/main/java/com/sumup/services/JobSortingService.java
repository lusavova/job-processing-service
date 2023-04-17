package com.sumup.services;

import com.sumup.services.models.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class JobSortingService {
    public List<Task> sort(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return tasks;
        }

        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, Integer> incomingEdges = new HashMap<>();
        for (Task task : tasks) {
            graph.putIfAbsent(task.getName(), new HashSet<>());
            incomingEdges.putIfAbsent(task.getName(), 0);
            for (String dependency : task.getDependencies()) {
                if (!graph.containsKey(dependency))
                    graph.put(dependency, new HashSet<>());
                graph.get(dependency).add(task.getName());
                incomingEdges.merge(task.getName(), 1, Integer::sum);
            }
        }

        List<String> sortedTasks = sortTasks(graph, incomingEdges);
        if (sortedTasks.size() != tasks.size()) {
            throw new IllegalArgumentException("Tasks contain cyclic dependencies.");
        }

        return mapTasksToSortedTasks(tasks, sortedTasks);
    }

    private List<Task> mapTasksToSortedTasks(List<Task> tasks, List<String> sortedTasks) {
        Map<String, Task> taskMap = tasks.stream()
                .collect(Collectors.toMap(Task::getName, Function.identity()));

        return sortedTasks.stream()
                .map(taskMap::get)
                .collect(Collectors.toList());
    }

    private List<String> sortTasks(final Map<String, Set<String>> graph, final Map<String, Integer> incomingEdges) {
        Queue<String> queue = graph.keySet().stream()
                .filter(task -> incomingEdges.get(task) == 0)
                .collect(Collectors.toCollection(ArrayDeque::new));

        List<String> sortedTasks = new ArrayList<>();
        while (!queue.isEmpty()) {
            String task = queue.poll();
            sortedTasks.add(task);
            for (String dependentTask : graph.get(task)) {
                incomingEdges.put(dependentTask, incomingEdges.get(dependentTask) - 1);
                if (incomingEdges.get(dependentTask) == 0) {
                    queue.offer(dependentTask);
                }
            }
        }

        return sortedTasks;
    }
}
