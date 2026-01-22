import java.util.Scanner;

public class Callie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printLine();
        System.out.println("Hello, I'm Callie! It's great to see you around today.\nWhat can I do for you?\n");
        printLine();

        Task[] store = new Task[100];
        int counter = 0;

        while (true) {
            String input = sc.nextLine();
            printLine();

            // exit
            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            // list
            if (input.equals("list")) {
                System.out.println(" Here are your current tasks in a list:");
                for (int i = 0; i < counter; i++) {
                    int j = i + 1;
                    System.out.println(j + ". " + store[i].toString());
                }
            }

            // mark
            else if (input.startsWith("mark") || input.startsWith("unmark")) {
                // process inputs
                String[] parts = input.split(" ");

                // error handling
                // error 1: no task specified
                if (parts.length < 2) {
                    System.out.println(" Please specify your task.");
                    printLine();
                    continue;
                }

                // error 2: bad task specified
                int taskNumber = Integer.parseInt(parts[1]);
                if (taskNumber < 1 || taskNumber > counter) {
                    System.out.println(" Task does not exist.");
                    printLine();
                    continue;
                }
                int index = taskNumber - 1;

                Task task = store[index];
                if (input.startsWith("mark")) {
                    if (task.isDone) {
                        System.out.println(" Task is already done.");
                    } else {
                        task.done();
                        System.out.println(" I've marked this task as done.");
                        System.out.println(taskNumber + ". " + task);
                    }
                } else {
                    if (!task.isDone) {
                        System.out.println(" Task is already unmarked.");
                    } else {
                        task.reset();
                        System.out.println(" I've unmarked this task.");
                        System.out.println(taskNumber + ". " + task);
                    }
                }
            }

            // add some task
            else {
                // add a deadline
                if (input.startsWith("deadline")) {
                    String[] parts = input.split(" by ");
                    if (parts.length < 2) {
                        System.out.println(" Please specify your task with the word 'by'.");
                        printLine();
                        continue;
                    }
                    Deadline newTask = new Deadline(parts[0].substring(9), parts[1]);
                    store[counter] = newTask;
                    System.out.println("added: " + newTask);
                }

                //add an event
                else if (input.startsWith("event")) {
                    String[] parts = input.split(" from ");
                    if (parts.length < 2) {
                        System.out.println(" Please specify your task with the word 'from' and 'to'.");
                        printLine();
                        continue;
                    }
                    String[] dates = parts[1].split(" to ");
                    if (dates.length < 2) {
                        System.out.println(" Please specify your task with the word 'from' and 'to'.");
                        printLine();
                        continue;
                    }
                    Event newTask = new Event(parts[0].substring(6), dates[0], dates[1]);
                    store[counter] = newTask;
                    System.out.println("added: " + newTask);
                }

                // add a 'ToDo'
                else if (input.startsWith("todo")) {
                    String ToDoName = input.substring(5);
                    ToDo newTask = new ToDo(ToDoName);
                    store[counter] = newTask;
                    System.out.println("added: " + newTask);
                }

                // reject everything else
                else {
                    System.out.println(" Please specify your task relevant task type.");
                    printLine();
                    continue;
                }
                counter++;
            }
            printLine();
        }

        // shutdown protocols
        sc.close();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
