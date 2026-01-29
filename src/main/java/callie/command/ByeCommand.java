package callie.command;

import callie.storage.Storage;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to exit the application.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
