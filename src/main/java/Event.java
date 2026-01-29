import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs over a specific time period.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected LocalDate end;
    protected LocalDate start;

    /**
     * Constructs an Event task with a name, start time, and end time.
     *
     * @param name  The description of the event.
     * @param start The start time of the event.
     * @param end   The end time of the event.
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start time for this event.
     *
     * @return The start time.
     */
    public String getStart() {
        return start.toString();
    }

    /**
     * Returns the end time for this event.
     *
     * @return The end time.
     */
    public String getEnd() {
        return end.toString();
    }

    /**
     * Returns the start date.
     *
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return start;
    }

    /**
     * Returns the end date.
     *
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return end;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string indicating the task type, completion status,
     *         event description, and time period.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[E] [X] " + name + " (from: " + start.format(OUTPUT_FORMAT)
                    + " to: " + end.format(OUTPUT_FORMAT) + ")";
        }
        else {
            return "[E] [ ] " + name + " (from: " + start.format(OUTPUT_FORMAT)
                    + " to: " + end.format(OUTPUT_FORMAT) + ")";
        }
    }

}
