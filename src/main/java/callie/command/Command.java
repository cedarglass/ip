package callie.command;

import java.io.IOException;

import callie.storage.Storage;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Represents a user command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI to show messages.
     * @param storage The storage handler for persistence.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether this command exits the application.
     *
     * @return True if the command exits.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Saves tasks to disk, showing an error if saving fails.
     *
     * @param tasks   The tasks to save.
     * @param storage The storage handler.
     * @param ui      The UI to show errors.
     */
    protected void saveTasks(TaskList tasks, Storage storage, Ui ui) {
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (IOException e) {
            ui.showMessage(" Sorry, I couldn't save your tasks just now.");
        }
    }
}
