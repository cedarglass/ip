package callie.ui;

import java.util.Scanner;

import callie.task.TaskList;

/**
 * Handles user interaction formatting for the chatbot.
 */
public abstract class Ui {
    private final Scanner scanner;

    /**
     * Creates a UI with a provided scanner.
     *
     * @param scanner The scanner to read input from.
     */
    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prints a generic message.
     *
     * @param message The message to show.
     */
    public void showMessage(String message) {
    }

    /**
     * Prints the current list of tasks.
     *
     * @param tasks The tasks to show.
     */
    public void showTaskList(TaskList tasks) {}

    /**
     * Prints a list of tasks filtered by a search string.
     *
     * @param filteredTasks The tasks to show.
     */
    public void showFilteredTaskList(TaskList filteredTasks) {}
}
