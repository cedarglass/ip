package callie.command;

import callie.storage.Storage;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to clear all tasks.
 */
public class ClearCommand extends Command {
    /**
     * Executes the clear command.
     *
     * @param tasks   The task list to update.
     * @param ui      The UI to show messages.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int removedCount = tasks.getSize();
        tasks.clearTasks();
        ui.showMessage(" Okay! I cleared " + removedCount + " task(s).");
        saveTasks(tasks, storage, ui);
    }
}
