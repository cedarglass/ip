package callie.command;

import callie.storage.Storage;
import callie.task.TaskList;
import callie.task.ToDo;
import callie.ui.Ui;

/**
 * Command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private final String name;

    /**
     * Creates a todo command.
     *
     * @param name The task description.
     */
    public AddTodoCommand(String name) {
        this.name = name;
    }

    /**
     * Executes the add-todo command.
     *
     * @param tasks   The task list to update.
     * @param ui      The UI to show messages.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo newTask = new ToDo(name);
        tasks.addTask(newTask);
        ui.showMessage("added: " + newTask);
        saveTasks(tasks, storage, ui);
    }
}
