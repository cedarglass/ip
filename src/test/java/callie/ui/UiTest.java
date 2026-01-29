package callie.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import callie.task.TaskList;
import callie.task.ToDo;

public class UiTest {
    @Test
    public void showMessages_printsToStdout() {
        Ui ui = new Ui(new java.util.Scanner(new ByteArrayInputStream(new byte[0])));
        String output = captureOutput(() -> {
            ui.showLine();
            ui.showMessage(" hello");
            ui.showLoadingError();
            ui.showWelcome();
            ui.showGoodbye();
        });
        assertTrue(output.contains("____________________________________________________________"));
        assertTrue(output.contains(" hello"));
        assertTrue(output.contains(" Sorry, I couldn't load your tasks."));
        assertTrue(output.contains("Hello, I'm Callie!"));
        assertTrue(output.contains(" Bye. Hope to see you again soon!"));
    }

    @Test
    public void showTaskList_printsTasks() {
        TaskList list = new TaskList(new java.util.ArrayList<>());
        list.addTask(new ToDo("read book"));
        list.addTask(new ToDo("return book"));
        Ui ui = new Ui(new java.util.Scanner(new ByteArrayInputStream(new byte[0])));
        String output = captureOutput(() -> ui.showTaskList(list));
        assertTrue(output.contains("1. [T] [ ] read book"));
        assertTrue(output.contains("2. [T] [ ] return book"));
    }

    @Test
    public void readCommand_readsNextLine() {
        ByteArrayInputStream in = new ByteArrayInputStream("list\n".getBytes(StandardCharsets.UTF_8));
        Ui ui = new Ui(new java.util.Scanner(in));
        assertEquals("list", ui.readCommand());
        ui.close();
    }

    private String captureOutput(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            action.run();
        } finally {
            System.setOut(originalOut);
        }
        return out.toString();
    }
}
