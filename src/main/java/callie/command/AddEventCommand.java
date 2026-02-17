package callie.command;

import java.time.LocalDateTime;

import callie.storage.Storage;
import callie.task.Event;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * Command to add an event task.
 */
public class AddEventCommand extends Command {
    private final String name;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates an event command.
     *
     * @param name  The task description.
     * @param start The start date.
     * @param end   The end date.
     */
    public AddEventCommand(String name, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the add-event command.
     *
     * @param tasks   The task list to update.
     * @param ui      The UI to show messages.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(name, start, end);
        tasks.addTask(newTask);
        ui.showMessage("added: " + newTask);
        saveTasks(tasks, storage, ui);
    }
}
