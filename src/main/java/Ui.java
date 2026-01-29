/**
 * Handles user interaction formatting for the chatbot.
 */
public class Ui {
    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello, I'm Callie! It's great to see you around today.\nWhat can I do for you?\n");
        showLine();
    }

    /**
     * Prints the goodbye message.
     */
    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints a standard divider line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a generic message.
     *
     * @param message The message to show.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the current list of tasks.
     *
     * @param tasks The tasks to show.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(" Here are your current tasks in a list:");
        System.out.println(tasks.toString());
    }

    /**
     * Prints a loading error message.
     */
    public void showLoadingError() {
        System.out.println(" Sorry, I couldn't load your tasks.");
    }
}
