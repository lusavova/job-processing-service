import com.sumup.api.dto.TaskDTO;
import com.sumup.api.mappers.JobFormatter;
import com.sumup.api.mappers.JsonJobFormatter;
import com.sumup.api.mappers.OutputFormat;
import com.sumup.api.mappers.ScriptJobFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JobFormatterTest {

    @Test
    public void testFromType() {
        JobFormatter formatter = JobFormatter.fromType(OutputFormat.JSON);
        Assertions.assertTrue(formatter instanceof JsonJobFormatter, "Formatter should be an instance of JsonJobFormatter");

        formatter = JobFormatter.fromType(OutputFormat.SCRIPT);
        Assertions.assertTrue(formatter instanceof ScriptJobFormatter, "Formatter should be an instance of ScriptJobFormatter");
    }

    @Test
    public void testJsonJobFormatter() {
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("task-1", "command-1", new ArrayList<>()));
        tasks.add(new TaskDTO("task-2", "command-2", new ArrayList<>()));

        String expected = "[{\"name\":\"task-1\",\"command\":\"command-1\"},{\"name\":\"task-2\",\"command\":\"command-2\"}]";

        JobFormatter formatter = new JsonJobFormatter();
        String result = formatter.format(tasks);

        Assertions.assertEquals(expected, result, "JSON formatting is incorrect");
    }

    @Test
    public void testScriptJobFormatter() {
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO("task-1", "command-1", new ArrayList<>()));
        tasks.add(new TaskDTO("task-2", "command-2", new ArrayList<>()));

        String expected = "command-1"+ System.lineSeparator()+"command-2";

        JobFormatter formatter = new ScriptJobFormatter();
        String result = formatter.format(tasks);

        Assertions.assertEquals(expected, result, "SCRIPT formatting is incorrect");
    }
}
