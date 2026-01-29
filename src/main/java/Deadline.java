import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected LocalDate deadline;

    /**
     * Constructs a Deadline task with a name and deadline.
     *
     * @param name     The description of the task.
     * @param deadline The deadline by which the task should be completed.
     */
    public Deadline(String name, LocalDate deadline) {
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
     * Returns the deadline as a LocalDate.
     *
     * @return The deadline date.
     */
    public LocalDate getDeadlineDate() {
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
        else {
            return "[D] [ ] " + name + " (by: " + deadline.format(OUTPUT_FORMAT) + ")";
        }
    }
}
