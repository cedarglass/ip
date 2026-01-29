/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a mark command.
     *
     * @param taskNumber One-based task number.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new IllegalArgumentException("wait, your task doesn't exist!");
        }
        int index = taskNumber - 1;
        Task task = tasks.getTask(index);
        if (task.isDone()) {
            ui.showMessage(" Task is already done.");
            return;
        }
        task.done();
        ui.showMessage(" I've marked this task as done.");
        ui.showMessage(taskNumber + ". " + task);
        saveTasks(tasks, storage, ui);
    }
}
