/**
 * Represents an event task that occurs over a specific time period.
 */
public class Event extends Task {
    protected String end;
    protected String start;

    /**
     * Constructs an Event task with a name, start time, and end time.
     *
     * @param name  The description of the event.
     * @param start The start time of the event.
     * @param end   The end time of the event.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
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
            return "[E] [X] " + name + " (from: " + start + " to: " + end + ")";
        }
        else {
            return "[E] [ ] " + name + " (from: " + start + " to: " + end + ")";
        }
    }

}
