import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main entry point for the Callie chatbot application.
 * <p>
 * Callie reads user commands from standard input, processes task-related
 * commands (todo, deadline, event, mark, unmark, list), and prints responses
 * to standard output until the user exits with the command "bye".
 */
public class Callie {

    /**
     * Runs the chatbot interaction loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        Parser parser = new Parser();
        ui.showWelcome();

        Storage storage = new Storage("./data/callie.txt");
        ArrayList<Task> store = storage.loadTasks();

        while (true) {
            String input = sc.nextLine();
            ui.showLine();

            try {
                // exit
                if (parser.isBye(input)) {
                    ui.showGoodbye();
                    break;
                }

                // list
                if (parser.isList(input)) {
                    ui.showTaskList(store);
                }

                // mark
                else if (parser.isMark(input) || parser.isUnmark(input)) {
                    int taskNumber = parser.parseIndex(input, "umm... you need to specify your task.");

                    // error 2: bad task specified
                    if (taskNumber < 1 || taskNumber > store.size()) {
                        throw new IllegalArgumentException("wait, your task doesn't exist!");
                    }
                    int index = taskNumber - 1;

                    Task task = store.get(index);
                    if (parser.isMark(input)) {
                        if (task.isDone) {
                            ui.showMessage(" Task is already done.");
                        } else {
                            task.done();
                            ui.showMessage(" I've marked this task as done.");
                            ui.showMessage(taskNumber + ". " + task);
                            saveTasks(storage, store);
                        }
                    } else {
                        if (!task.isDone) {
                            ui.showMessage(" Task is already unmarked.");
                        } else {
                            task.reset();
                            ui.showMessage(" I've unmarked this task.");
                            ui.showMessage(taskNumber + ". " + task);
                            saveTasks(storage, store);
                        }
                    }
                }

                // delete some task
                else if (parser.isDelete(input)) {
                    int taskNumber = parser.parseIndex(input,
                            "umm... you need to specify which task to delete.");
                    if (taskNumber < 1 || taskNumber > store.size()) {
                        throw new IllegalArgumentException("wait, your task doesn't exist!");
                    }

                    Task removed = store.remove(taskNumber - 1);

                    ui.showMessage(" sure, happy to make your to-do lists shorter :)");
                    ui.showMessage(" removed: " + removed);
                    saveTasks(storage, store);
                }

                // clear all tasks
                else if (parser.isClear(input)) {
                    int removedCount = store.size();
                    store.clear();
                    ui.showMessage(" Okay! I cleared " + removedCount + " task(s).");
                    saveTasks(storage, store);
                }

                // add some task
                else {
                    // add a deadline
                    if (parser.isDeadline(input)) {
                        String[] parts = parser.parseDeadline(input);
                        LocalDate deadlineDate = parseDate(parts[1]);
                        Deadline newTask = new Deadline(parts[0], deadlineDate);
                        store.add(newTask);
                        ui.showMessage("added: " + newTask);
                        saveTasks(storage, store);
                    }

                    // add an event
                    else if (parser.isEvent(input)) {
                        String[] parts = parser.parseEvent(input);
                        LocalDate startDate = parseDate(parts[1]);
                        LocalDate endDate = parseDate(parts[2]);
                        Event newTask = new Event(parts[0], startDate, endDate);
                        store.add(newTask);
                        ui.showMessage("added: " + newTask);
                        saveTasks(storage, store);
                    }

                    // add a 'ToDo'
                    else if (parser.isTodo(input)) {
                        String todoName = parser.parseTodoName(input);
                        ToDo newTask = new ToDo(todoName);
                        store.add(newTask);
                        ui.showMessage("added: " + newTask);
                        saveTasks(storage, store);
                    }

                    // reject everything else
                    else {
                        throw new IllegalArgumentException(" Please specify your relevant task type.");
                    }
                }
            } catch (IllegalArgumentException e) {
                ui.showMessage(e.getMessage());
            }
            ui.showLine();
        }

        // shutdown protocols
        sc.close();
    }

    /**
     * Prints a visual divider line to separate chatbot outputs.
     */
    private static void saveTasks(Storage storage, ArrayList<Task> store) {
        try {
            storage.saveTasks(store);
        } catch (IOException e) {
            System.out.println(" Sorry, I couldn't save your tasks just now.");
        }
    }

    private static LocalDate parseDate(String dateText) {
        try {
            return LocalDate.parse(dateText);
        } catch (Exception e) {
            throw new IllegalArgumentException(" Please use date format yyyy-mm-dd.");
        }
    }
}
