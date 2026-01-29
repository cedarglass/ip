package callie.task;

/**
 * Represents a simple to-do task without any date or time constraints.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given name.
     *
     * @param name The description of the to-do task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A formatted string indicating the task type, completion status,
     *         and task description.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[T] [X] " + name;
        }
        return "[T] [ ] " + name;
    }
}
