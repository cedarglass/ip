package callie.command;

import callie.storage.Storage;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
