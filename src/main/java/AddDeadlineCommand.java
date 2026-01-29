import java.time.LocalDate;

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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(name, deadline);
        tasks.addTask(newTask);
        ui.showMessage("added: " + newTask);
        saveTasks(tasks, storage, ui);
    }
}
