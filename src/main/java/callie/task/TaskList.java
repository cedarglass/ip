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
