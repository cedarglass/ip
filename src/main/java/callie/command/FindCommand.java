package callie.command;

import java.util.ArrayList;

import callie.storage.Storage;
import callie.task.Task;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to find all tasks containing a search string.
 */
public class FindCommand extends Command {
    private final String searchString;

    /**
     * Creates a find command.
     *
     * @param searchString a search query.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matches = new ArrayList<>();
        String normalizedSearch = searchString.toLowerCase();
        for (Task task : tasks.getTasks()) {
            if (task.getName().toLowerCase().contains(normalizedSearch)) {
                matches.add(task);
            }
        }

        if (matches.isEmpty()) {
            ui.showMessage(" I couldn't find any matches for '" + searchString + "'!");
            ui.showMessage(" Are you sure you got that right?");
        } else {
            ui.showFilteredTaskList(new TaskList(matches));
        }
    }
}
