/**
 * Represents a task that must be completed by a specific deadline.
 */
public class Deadline extends Task {
    protected String deadline;

    /**
     * Constructs a Deadline task with a name and deadline.
     *
     * @param name     The description of the task.
     * @param deadline The deadline by which the task should be completed.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline for this task.
     *
     * @return The deadline string.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string indicating the task type, completion status,
     *         task description, and deadline.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[D] [X] " + name + " (by: " + deadline + ")";
        }
        else {
            return "[D] [ ] " + name + " (by: " + deadline + ")";
        }
    }
}
