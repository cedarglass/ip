package callie.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import callie.storage.Storage;
import callie.task.Task;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to delete multiple tasks.
 */
public class BulkDeleteCommand extends Command {
    private final List<Integer> taskNumbers;

    /**
     * Creates a bulk delete command.
     *
     * @param taskNumbers One-based task numbers.
     */
    public BulkDeleteCommand(List<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
    }

    /**
     * Executes the bulk delete command.
     *
     * @param tasks   The task list to update.
     * @param ui      The UI to show messages.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        validateIndices(tasks);
        List<Integer> uniqueIndices = new ArrayList<>(new LinkedHashSet<>(taskNumbers));
        uniqueIndices.sort(Collections.reverseOrder());

        ui.showMessage(" sure, happy to make your to-do lists shorter :)");
        for (int taskNumber : uniqueIndices) {
            Task removed = tasks.removeTask(taskNumber - 1);
            ui.showMessage(" removed: " + removed);
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
