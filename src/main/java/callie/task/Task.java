package callie.task;

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
        return "[ ] " + name;
    }

    /**
     * Marks the task as completed.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void reset() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return True if the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the task description.
     *
     * @return The task name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done The new completion status.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }
}
