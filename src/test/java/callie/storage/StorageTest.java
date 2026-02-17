package callie.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import callie.task.Deadline;
import callie.task.Event;
import callie.task.Task;
import callie.task.ToDo;

public class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    public void load_missingFile_returnsEmptyList() {
        Storage storage = new Storage(tempDir.resolve("data/callie.txt").toString());
        ArrayList<Task> tasks = storage.loadTasks();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void saveThenLoad_roundTrip_preservesTasks() throws IOException {
        Storage storage = new Storage(tempDir.resolve("data/callie.txt").toString());

        ArrayList<Task> tasks = new ArrayList<>();
        ToDo todo = new ToDo("read book");
        todo.setDone(true);
        tasks.add(todo);
        tasks.add(new Deadline("return book", LocalDateTime.parse("2019-06-06T12:00")));
        Event event = new Event("project meeting", LocalDateTime.parse("2019-08-06T09:00"),
                LocalDateTime.parse("2019-08-07T18:00"));
        event.setDone(true);
        tasks.add(event);

        storage.saveTasks(tasks);
        ArrayList<Task> loaded = storage.loadTasks();

        assertEquals(3, loaded.size());
        assertInstanceOf(ToDo.class, loaded.get(0));
        assertTrue(loaded.get(0).isDone());
        assertEquals("read book", loaded.get(0).getName());

        assertInstanceOf(Deadline.class, loaded.get(1));
        Deadline loadedDeadline = (Deadline) loaded.get(1);
        assertFalse(loadedDeadline.isDone());
        assertEquals("return book", loadedDeadline.getName());
        assertEquals("2019-06-06T12:00", loadedDeadline.getDeadline());

        assertInstanceOf(Event.class, loaded.get(2));
        Event loadedEvent = (Event) loaded.get(2);
        assertTrue(loadedEvent.isDone());
        assertEquals("project meeting", loadedEvent.getName());
        assertEquals("2019-08-06T09:00", loadedEvent.getStart());
        assertEquals("2019-08-07T18:00", loadedEvent.getEnd());
    }

    @Test
    public void load_corruptedLines_skipsInvalidEntries() throws IOException {
        Path filePath = tempDir.resolve("data/callie.txt");
        Files.createDirectories(filePath.getParent());
        List<String> lines = List.of(
                "T | 1 | read book",
                "D | 0 | return book | 2019-06-06T12:00",
                "E | 0 | project meeting | 2019-08-06T09:00 | 2019-08-07T18:00",
                "X | 1 | unknown type",
                "D | 0 | missing deadline",
                "E | 1 | missing end | 2019-08-06"
        );
        Files.write(filePath, lines);

        Storage storage = new Storage(filePath.toString());
        ArrayList<Task> loaded = storage.loadTasks();

        assertEquals(3, loaded.size());
        assertInstanceOf(ToDo.class, loaded.get(0));
        assertInstanceOf(Deadline.class, loaded.get(1));
        assertInstanceOf(Event.class, loaded.get(2));
    }
}
