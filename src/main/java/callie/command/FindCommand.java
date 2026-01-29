package callie.command;

import callie.storage.Storage;
import callie.task.Task;
import callie.task.TaskList;
import callie.ui.Ui;

import java.util.ArrayList;

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
        ArrayList<Task> matches = new ArrayList<Task>();
        for (Task task : tasks.getTasks()) {
            if (task.getName().toLowerCase().contains(searchString.toLowerCase())) {
                matches.add(task);
            }
        }
        ui.showFilteredTaskList(new TaskList(matches));
    }
}