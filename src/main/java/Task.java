// use OOP to clarify code
public class Task {
    protected boolean isDone;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + name;
        }
        else {
            return "[ ] " + name;
        }
    }

    protected void done() {
        this.isDone = true;
    }

    protected void reset() {
        this.isDone = false;
    }
}