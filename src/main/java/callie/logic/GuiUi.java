package callie.logic;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Collects UI messages for GUI rendering.
 * Extends the UI class to avoid renaming existing methods.
 */
public class GuiUi extends Ui {
    private final List<String> messages = new ArrayList<>();

    /**
     * Creates a GUI UI with a dummy input stream.
     */
    public GuiUi() {
        super(new Scanner(new ByteArrayInputStream(new byte[0])));
    }

    /**
     * Returns the concatenated response and clears the buffer.
     *
     * @return The combined response string.
     */
    public String flushMessages() {
        if (messages.isEmpty()) {
            return "";
        }
        String combined = String.join(System.lineSeparator(), messages);
        messages.clear();
        return combined;
    }

    /**
     * Clears any collected messages without returning them.
     */
    public void reset() {
        messages.clear();
    }

    @Override
    public void showMessage(String message) {
        messages.add(message);
    }

    @Override
    public void showTaskList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            messages.add("Seems like your to-do list is empty!");
            return;
        }
        messages.add(" Here are your current tasks in a list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            int index = i + 1;
            messages.add(index + ". " + tasks.getTask(i));
        }
    }

    @Override
    public void showFilteredTaskList(TaskList filteredTasks) {
        messages.add(" Here are the matching tasks in your list:");
        for (int i = 0; i < filteredTasks.getSize(); i++) {
            int index = i + 1;
            messages.add(index + ". " + filteredTasks.getTask(i));
        }
    }

    @Override
    public void showGoodbye() {
        messages.add(" Bye. Hope to see you again soon!");
    }
}
