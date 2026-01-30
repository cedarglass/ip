package callie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import callie.storage.Storage;
import callie.task.TaskList;
import callie.task.ToDo;
import callie.ui.Ui;

public class CommandTest {
    @TempDir
    Path tempDir;

    @Test
    public void addTodoCommand_addsTaskAndSaves() throws Exception {
        Storage storage = new Storage(tempDir.resolve("data/callie.txt").toString());
        TaskList tasks = new TaskList(new ArrayList<>());
        RecordingUi ui = new RecordingUi();

        Command cmd = new AddTodoCommand("read book");
        cmd.execute(tasks, ui, storage);

        assertEquals(1, tasks.getSize());
        assertTrue(ui.messages.get(0).contains("added: [T] [ ] read book"));
        assertTrue(Files.exists(tempDir.resolve("data/callie.txt")));
    }

    @Test
    public void addDeadlineAndEvent_addsTasks() throws Exception {
        Storage storage = new Storage(tempDir.resolve("data/callie.txt").toString());
        TaskList tasks = new TaskList(new ArrayList<>());
        RecordingUi ui = new RecordingUi();

        new AddDeadlineCommand("return book", LocalDate.parse("2019-10-15"))
                .execute(tasks, ui, storage);
        new AddEventCommand("meeting", LocalDate.parse("2019-10-16"),
                LocalDate.parse("2019-10-17")).execute(tasks, ui, storage);

        assertEquals(2, tasks.getSize());
    }

    @Test
    public void markUnmarkDeleteClear_executeUpdatesTasks() throws Exception {
        Storage storage = new Storage(tempDir.resolve("data/callie.txt").toString());
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(new ToDo("a"));
        tasks.addTask(new ToDo("b"));
        RecordingUi ui = new RecordingUi();

        new MarkCommand(1).execute(tasks, ui, storage);
        new UnmarkCommand(1).execute(tasks, ui, storage);
        new DeleteCommand(2).execute(tasks, ui, storage);
        new ClearCommand().execute(tasks, ui, storage);

        assertEquals(0, tasks.getSize());
    }

    @Test
    public void byeCommand_isExitTrue() {
        Command cmd = new ByeCommand();
        assertTrue(cmd.isExit());
    }

    @Test
    public void listCommand_callsUi() {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(new ToDo("a"));
        RecordingUi ui = new RecordingUi();
        new ListCommand().execute(tasks, ui, new Storage(tempDir.resolve("data/callie.txt").toString()));
        assertEquals(1, ui.listShownCount);
    }

    @Test
    public void listCommand_isExitFalse() {
        Command cmd = new ListCommand();
        assertFalse(cmd.isExit());
    }

    @Test
    public void findCommand_filtersMatchingTasks() {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(new ToDo("read book"));
        tasks.addTask(new ToDo("return book"));
        tasks.addTask(new ToDo("wash clothes"));
        RecordingUi ui = new RecordingUi();

        new FindCommand("book").execute(tasks, ui, new Storage(tempDir.resolve("data/callie.txt").toString()));

        assertEquals(1, ui.filteredListShownCount);
        assertEquals(2, ui.lastFilteredSize);
    }

    @Test
    public void findCommand_emptyTaskList_printsMessage() {
        TaskList tasks = new TaskList(new ArrayList<>());
        RecordingUi ui = new RecordingUi();

        new FindCommand("book").execute(tasks, ui, new Storage(tempDir.resolve("data/callie.txt").toString()));
        assertTrue(ui.messages.get(0).contains("I couldn't find any matches for 'book'!"));
        assertTrue(ui.messages.get(1).contains(" Are you sure you got that right?"));
    }

    private static class RecordingUi extends Ui {
        private final List<String> messages = new ArrayList<>();
        private int listShownCount = 0;
        private int filteredListShownCount = 0;
        private int lastFilteredSize = 0;

        RecordingUi() {
            super(new java.util.Scanner(new ByteArrayInputStream(new byte[0])));
        }

        @Override
        public void showMessage(String message) {
            messages.add(message);
        }

        @Override
        public void showTaskList(TaskList tasks) {
            listShownCount += 1;
        }

        @Override
        public void showFilteredTaskList(TaskList filteredTasks) {
            filteredListShownCount += 1;
            lastFilteredSize = filteredTasks.getSize();
        }
    }
}
