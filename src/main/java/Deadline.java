public class Deadline extends Task {
    protected String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

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
