package callie.command;

import java.time.LocalDate;

import callie.storage.Storage;
import callie.task.Deadline;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String name;
    private final LocalDate deadline;

    /**
     * Creates a deadline command.
     *
     * @param name     The task description.
     * @param deadline The deadline date.
     */
    public AddDeadlineCommand(String name, LocalDate deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * Executes the add-deadline command.
     *
     * @param tasks   The task list to update.
     * @param ui      The UI to show messages.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(name, deadline);
        tasks.addTask(newTask);
        ui.showMessage("added: " + newTask);
        saveTasks(tasks, storage, ui);
    }
}
