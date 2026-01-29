package callie.command;

import callie.storage.Storage;
import callie.task.Task;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a delete command.
     *
     * @param taskNumber One-based task number.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks   The task list to update.
     * @param ui      The UI to show messages.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new IllegalArgumentException("wait, your task doesn't exist!");
        }
        Task removed = tasks.removeTask(taskNumber - 1);
        ui.showMessage(" sure, happy to make your to-do lists shorter :)");
        ui.showMessage(" removed: " + removed);
        saveTasks(tasks, storage, ui);
    }
}
