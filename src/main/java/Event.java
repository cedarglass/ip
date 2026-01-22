public class Event extends Task {
    protected String end;
    protected String start;


    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

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
