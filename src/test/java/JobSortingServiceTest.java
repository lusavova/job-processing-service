import com.sumup.services.JobSortingService;
import com.sumup.services.models.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JobSortingServiceTest {

    private JobSortingService jobSortingService;

    @BeforeEach
    public void setUp() {
        jobSortingService = new JobSortingService();
    }

    @Test
    @DisplayName("Test sort method with no tasks")
    public void testSortMethodWithNoTasks() {
        List<Task> tasks = new ArrayList<>();
        List<Task> sortedTasks = jobSortingService.sort(tasks);
        Assertions.assertEquals(0, sortedTasks.size());
    }

    @Test
    @DisplayName("Test sort method with tasks containing cyclic dependencies")
    public void testSortMethodWithCyclicDependencies() {
        Task task1 = new Task("task-1", "", List.of("task-2"));
        Task task2 = new Task("task-2","", List.of("task-3"));
        Task task3 = new Task("task-3","", List.of("task-1"));
        List<Task> tasks = Arrays.asList(task1, task2, task3);

        Assertions.assertThrows(IllegalArgumentException.class, () -> jobSortingService.sort(tasks));
    }

    @Test
    @DisplayName("Test sort method with tasks containing no cyclic dependencies")
    public void testSortMethodWithNoCyclicDependencies() {
        Task task1 = new Task("task-1","touch /tmp/file1", new ArrayList<>());
        Task task2 = new Task("task-2","cat /tmp/file1", List.of("task-3"));
        Task task3 = new Task("task-3","echo 'Hello World!' > /tmp/file1", List.of("task-1"));
        Task task4 = new Task("task-4","rm /tmp/file1", List.of("task-2", "task-3"));
        List<Task> tasks = Arrays.asList(task1, task2, task3, task4);

        List<Task> sortedTasks = jobSortingService.sort(tasks);
        List<String> expectedTaskNames = Arrays.asList("task-1", "task-3", "task-2", "task-4");

        Assertions.assertEquals(expectedTaskNames.size(), sortedTasks.size());
        for (int i = 0; i < expectedTaskNames.size(); i++) {
            String expectedTaskName = expectedTaskNames.get(i);
            String actualTaskName = sortedTasks.get(i).getName();
            Assertions.assertEquals(expectedTaskName, actualTaskName);
        }
    }
}

