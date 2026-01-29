/**
 * Handles user interaction formatting for the chatbot.
 */
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    /**
     * Creates a UI with standard input.
     */
    public Ui() {
        this(new Scanner(System.in));
    }

    /**
     * Creates a UI with a provided scanner.
     *
     * @param scanner The scanner to read input from.
     */
    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }
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

    /**
     * Reads the next command from the user.
     *
     * @return The input line.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the input scanner.
     */
    public void close() {
        scanner.close();
    }
}
