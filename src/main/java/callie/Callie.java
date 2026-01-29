package callie;

import callie.command.Command;
import callie.parser.Parser;
import callie.storage.Storage;
import callie.task.TaskList;
import callie.ui.Ui;

/**
 * The main entry point for the Callie chatbot application.
 * <p>
 * Callie reads user commands from standard input, processes task-related
 * commands, and prints responses to standard output until the user exits.
 */
public class Callie {

    /**
     * Runs the chatbot interaction loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        Storage storage = new Storage("./data/callie.txt");
        TaskList tasks = new TaskList(storage.loadTasks());

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            ui.showLine();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (IllegalArgumentException e) {
                ui.showMessage(e.getMessage());
            }
            if (!isExit) {
                ui.showLine();
            }
        }

        ui.close();
    }
}
