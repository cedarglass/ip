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
        ui.showWelcome();

        Storage storage = new Storage("./data/callie.txt");
        ArrayList<Task> store = storage.loadTasks();

        while (true) {
            String input = sc.nextLine();
            ui.showLine();

            try {
                // exit
                if (input.equals("bye")) {
                    ui.showGoodbye();
                    break;
                }

                // list
                if (input.equals("list")) {
                    ui.showTaskList(store);
                }

                // mark
                else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    // process inputs
                    String[] parts = input.split(" ");

                    // error handling
                    // error 1: no task specified
                    if (parts.length < 2) {
                        throw new IllegalArgumentException("umm... you need to specify your task.");
                    }

                    // error 2: bad task specified
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber < 1 || taskNumber > store.size()) {
                        throw new IllegalArgumentException("wait, your task doesn't exist!");
                    }
                    int index = taskNumber - 1;

                    Task task = store.get(index);
                    if (input.startsWith("mark")) {
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
                else if (input.startsWith("delete")) {
                    String[] parts = input.split(" ");
                    if (parts.length < 2) {
                        throw new IllegalArgumentException("umm... you need to specify which task to delete.");
                    }

                    int taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber < 1 || taskNumber > store.size()) {
                        throw new IllegalArgumentException("wait, your task doesn't exist!");
                    }

                    Task removed = store.remove(taskNumber - 1);

                    ui.showMessage(" sure, happy to make your to-do lists shorter :)");
                    ui.showMessage(" removed: " + removed);
                    saveTasks(storage, store);
                }

                // clear all tasks
                else if (input.equals("clear")) {
                    int removedCount = store.size();
                    store.clear();
                    ui.showMessage(" Okay! I cleared " + removedCount + " task(s).");
                    saveTasks(storage, store);
                }

                // add some task
                else {
                    // add a deadline
                    if (input.startsWith("deadline")) {
                        String[] parts = input.split(" by ");
                        if (parts.length < 2) {
                            throw new IllegalArgumentException(" Please specify your task with the word 'by'.");
                        }
                        String dateText = parts[1].trim();
                        LocalDate deadlineDate = parseDate(dateText);
                        Deadline newTask = new Deadline(parts[0].substring(9), deadlineDate);
                        store.add(newTask);
                        ui.showMessage("added: " + newTask);
                        saveTasks(storage, store);
                    }

                    // add an event
                    else if (input.startsWith("event")) {
                        String[] parts = input.split(" from ");
                        if (parts.length < 2) {
                            throw new IllegalArgumentException(" Please specify your task with the word 'from' and 'to'.");
                        }
                        String[] dates = parts[1].split(" to ");
                        if (dates.length < 2) {
                            throw new IllegalArgumentException(" Please specify your task with the word 'from' and 'to'.");
                        }
                        LocalDate startDate = parseDate(dates[0].trim());
                        LocalDate endDate = parseDate(dates[1].trim());
                        Event newTask = new Event(parts[0].substring(6), startDate, endDate);
                        store.add(newTask);
                        ui.showMessage("added: " + newTask);
                        saveTasks(storage, store);
                    }

                    // add a 'ToDo'
                    else if (input.startsWith("todo")) {
                        // remove all leading and trailing whitespaces
                        String ToDoName = input.substring(4).trim();
                        if (ToDoName.isEmpty()) {
                            throw new IllegalArgumentException("umm... you need to specify a task name.");
                        }
                        ToDo newTask = new ToDo(ToDoName);
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
