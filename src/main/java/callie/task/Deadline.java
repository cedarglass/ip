package callie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline task with a name and deadline.
     *
     * @param name     The description of the task.
     * @param deadline The deadline by which the task should be completed.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline for this task.
     *
     * @return The deadline string.
     */
    public String getDeadline() {
        return deadline.toString();
    }

    /**
     * Returns the deadline as a LocalDateTime.
     *
     * @return The deadline date-time.
     */
    public LocalDateTime getDeadlineDate() {
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
            return "[D] [X] " + name + " (by: " + deadline.format(OUTPUT_FORMAT) + ")";
        }
        return "[D] [ ] " + name + " (by: " + deadline.format(OUTPUT_FORMAT) + ")";
    }
}
