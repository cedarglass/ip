import java.time.LocalDate;

/**
 * Command to add an event task.
 */
public class AddEventCommand extends Command {
    private final String name;
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Creates an event command.
     *
     * @param name  The task description.
     * @param start The start date.
     * @param end   The end date.
     */
    public AddEventCommand(String name, LocalDate start, LocalDate end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(name, start, end);
        tasks.addTask(newTask);
        ui.showMessage("added: " + newTask);
        saveTasks(tasks, storage, ui);
    }
}
