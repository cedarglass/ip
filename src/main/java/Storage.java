import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to disk.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates a storage handler with a relative path.
     *
     * @param relativePath Relative path to the data file.
     */
    public Storage(String relativePath) {
        this.filePath = Paths.get(relativePath);
    }

    /**
     * Loads tasks from disk. Missing files or folders are treated as empty data.
     *
     * @return The list of tasks loaded from disk.
     */
    public TaskList loadTasks() {
        ArrayList<Task> arrList = new ArrayList<>();
        TaskList tasks = new TaskList(arrList);

        // loads File
        if (!Files.exists(filePath)) {
            return tasks;
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(filePath); // reads File
        } catch (IOException e) {
            return tasks;
        }

        for (String line : lines) {
            Task parsed = parseLine(line);
            if (parsed != null) {
                tasks.addTask(parsed);
            }
        }

        return tasks;
    }

    /**
     * Saves tasks to disk, creating parent directories if required.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If writing fails.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        List<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            lines.add(formatLine(task));
        }
        Files.write(filePath, lines); // writes to File
    }

    private Task parseLine(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0].trim();
        String doneFlag = parts[1].trim();
        String name = parts[2].trim();
        boolean isDone = "1".equals(doneFlag);

        Task task;
        try {
            switch (type) {
            case "T":
                task = new ToDo(name);
                break;
            case "D":
                if (parts.length < 4) {
                    return null;
                }
                task = new Deadline(name, LocalDate.parse(parts[3].trim()));
                break;
            case "E":
                if (parts.length < 5) {
                    return null;
                }
                task = new Event(name, LocalDate.parse(parts[3].trim()),
                        LocalDate.parse(parts[4].trim()));
                break;
            default:
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        task.setDone(isDone);
        return task;
    }

    private String formatLine(Task task) {
        String doneFlag = task.isDone() ? "1" : "0";
        if (task instanceof ToDo) {
            return "T | " + doneFlag + " | " + task.getName();
        }
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + doneFlag + " | " + task.getName() + " | " + deadline.getDeadline();
        }
        if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + doneFlag + " | " + task.getName()
                    + " | " + event.getStart() + " | " + event.getEnd();
        }
        return "T | " + doneFlag + " | " + task.getName();
    }
}
