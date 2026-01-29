package callie.command;

import callie.storage.Storage;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param tasks   The task list to display.
     * @param ui      The UI to show messages.
     * @param storage The storage handler (unused).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
