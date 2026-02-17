package callie.command;

import java.util.List;

import callie.storage.Storage;
import callie.task.Task;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to mark multiple tasks as done.
 */
public class BulkMarkCommand extends Command {
    private final List<Integer> taskNumbers;

    /**
     * Creates a bulk mark command.
     *
     * @param taskNumbers One-based task numbers.
     */
    public BulkMarkCommand(List<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
    }

    /**
     * Executes the bulk mark command.
     *
     * @param tasks   The task list to update.
     * @param ui      The UI to show messages.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        validateIndices(tasks);
        ui.showMessage(" Let's mark your tasks.");
        for (int taskNumber : taskNumbers) {
            Task task = tasks.getTask(taskNumber - 1);
            if (task.isDone()) {
                ui.showMessage(String.format(" Task %d is already done.", taskNumber));
            } else {
                task.done();
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
