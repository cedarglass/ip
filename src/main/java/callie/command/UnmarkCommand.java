package callie.command;

import callie.storage.Storage;
import callie.task.Task;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Creates an unmark command.
     *
     * @param taskNumber One-based task number.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new IllegalArgumentException("wait, your task doesn't exist!");
        }
        int index = taskNumber - 1;
        Task task = tasks.getTask(index);
        if (!task.isDone()) {
            ui.showMessage(" Task is already unmarked.");
            return;
        }
        task.reset();
        ui.showMessage(" I've unmarked this task.");
        ui.showMessage(taskNumber + ". " + task);
        saveTasks(tasks, storage, ui);
    }
}
