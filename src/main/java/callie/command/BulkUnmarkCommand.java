package callie.command;

import java.util.List;

import callie.storage.Storage;
import callie.task.Task;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to unmark multiple tasks as not done.
 */
public class BulkUnmarkCommand extends Command {
    private final List<Integer> taskNumbers;

    /**
     * Creates a bulk unmark command.
     *
     * @param taskNumbers One-based task numbers.
     */
    public BulkUnmarkCommand(List<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
    }

    /**
     * Executes the bulk unmark command.
     *
     * @param tasks   The task list to update.
     * @param ui      The UI to show messages.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        validateIndices(tasks);
        for (int taskNumber : taskNumbers) {
            Task task = tasks.getTask(taskNumber - 1);
            if (!task.isDone()) {
                ui.showMessage(String.format(" Task %d is already unmarked.", taskNumber));
            } else {
                task.reset();
                ui.showMessage(" I've unmarked this task.");
                ui.showMessage(taskNumber + ". " + task);
            }
        }
        saveTasks(tasks, storage, ui);
    }

    /**
     * Validates that all indices are within the task list bounds.
     *
     * @param tasks The task list to check against.
     */
    private void validateIndices(TaskList tasks) {
        for (int taskNumber : taskNumbers) {
            if (taskNumber < 1 || taskNumber > tasks.getSize()) {
                throw new IllegalArgumentException("wait, your task doesn't exist!");
            }
        }
    }
}
