package callie.command;

import callie.storage.Storage;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasks   The task list (unused).
     * @param ui      The UI to show messages.
     * @param storage The storage handler (unused).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye! \n Hope to see you soon!");
    }

    /**
     * Indicates this command exits the application.
     *
     * @return True for exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
