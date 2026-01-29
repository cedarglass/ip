package callie.command;

import callie.storage.Storage;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to clear all tasks.
 */
public class ClearCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int removedCount = tasks.getSize();
        tasks.clearTasks();
        ui.showMessage(" Okay! I cleared " + removedCount + " task(s).");
        saveTasks(tasks, storage, ui);
    }
}
