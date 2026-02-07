package callie.logic;

import java.util.ArrayList;
import java.util.List;

import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Collects UI messages for GUI rendering.
 * Extends the UI class to avoid renaming existing methods.
 */
public class GuiUi extends Ui {
    private static final List<String> messages = new ArrayList<>();

    /**
     * Clears any collected messages and returns the concatenated response.
     *
     * @return The combined response string.
     */
    public static String flushMessages() {
        if (messages.isEmpty()) {
            return "";
        }
        String combined = String.join(System.lineSeparator(), messages);
        messages.clear();
        return combined;
    }

    @Override
    public void showMessage(String message) {
        messages.add(message);
    }

    @Override
    public void showTaskList(TaskList tasks) {
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
