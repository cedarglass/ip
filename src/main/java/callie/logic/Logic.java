package callie.logic;

import callie.command.Command;
import callie.parser.Parser;
import callie.storage.Storage;
import callie.task.TaskList;

/**
 * Handles the application's core logic and command execution.
 */
public class Logic {
    private final Storage storage;
    private final TaskList tasks;
    private boolean shouldExit;

    /**
     * Creates a Logic instance with storage and loaded tasks.
     *
     * @param storage The storage handler.
     */
    public Logic(Storage storage) {
        assert storage != null : "Storage must be initialized before Logic.";
        this.storage = storage;
        this.tasks = new TaskList(storage.loadTasks());
        this.shouldExit = false;
    }

    /**
     * Returns the welcome message.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello, I'm Callie! It's great to see you around today.\nWhat can I do for you?\n";
    }

    /**
     * Executes the user input and returns the response.
     *
     * @param input The raw user input.
     * @return The response to display.
     */
    public String getResponse(String input) {
        assert input != null : "User input should not be null.";
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, new GuiUi(), storage);
            shouldExit = command.isExit();

            // previously, in execute(), the UI would print the necessary messages.
            // however, the GUI now stores the messages in a String, and then returns it!
            return GuiUi.flushMessages();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns whether the last command requested exit.
     *
     * @return True if the app should exit.
     */
    public boolean isExitCommand() {
        return shouldExit;
    }
}
