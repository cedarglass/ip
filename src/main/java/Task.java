// use OOP to clarify code
/**
 * An abstract representation of a task.
 * <p>
 * A task has a name and a completion status. Subclasses define
 * specific task types such as ToDo, Deadline, and Event.
 */
public abstract class Task {
    /** Indicates whether the task has been completed. */
    protected boolean isDone;

    /** The description of the task. */
    protected String name;

    /**
     * Constructs a task with the given name.
     *
     * @param name The description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string showing the task's completion status and name.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + name;
        }
        else {
            return "[ ] " + name;
        }
    }

    /**
     * Marks the task as completed.
     */
    protected void done() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    protected void reset() {
        this.isDone = false;
    }
}