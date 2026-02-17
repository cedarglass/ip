package callie.task;

import java.util.ArrayList;

/**
 * Stores and manages a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a task list from an existing list.
     *
     * @param tasks The backing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks.
     *
     * @return The task count.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param index The zero-based index.
     * @return The task at that index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index should be within bounds.";
        return tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        if (isDuplicate(task)) {
            throw new IllegalArgumentException(" wait... this task already exists!");
        }
        tasks.add(task);
    }

    /**
     * Removes the task at the given index.
     *
     * @param index The zero-based index.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index should be within bounds.";
        return tasks.remove(index);
    }

    /**
     * Removes all tasks from the list.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Returns the backing list of tasks.
     *
     * @return The task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private boolean isDuplicate(Task task) {
        for (Task existing : tasks) {
            if (isSameTask(existing, task)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameTask(Task left, Task right) {
        if (!left.getClass().equals(right.getClass())) {
            return false;
        }
        if (!left.getName().equals(right.getName())) {
            return false;
        }
        if (left instanceof Deadline) {
            Deadline leftDeadline = (Deadline) left;
            Deadline rightDeadline = (Deadline) right;
            return leftDeadline.getDeadline().equals(rightDeadline.getDeadline());
        }
        if (left instanceof Event) {
            Event leftEvent = (Event) left;
            Event rightEvent = (Event) right;
            return leftEvent.getStart().equals(rightEvent.getStart())
                    && leftEvent.getEnd().equals(rightEvent.getEnd());
        }
        return true;
    }

    /**
     * Returns a multi-line string of tasks.
     *
     * @return The formatted task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
