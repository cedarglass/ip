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

            // exit
            if (input.equals("bye")) {
                printLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            // list
            if (input.equals("list")) {
                printLine();
                System.out.println(" Here are your current tasks in a list:");
                for (int i = 0; i < counter; i++) {
                    int j = i + 1;
                    System.out.println(j + ". " + store[i].toString());
                }
                printLine();
            }

            // mark
            else if (input.startsWith("mark") || input.startsWith("unmark")) {
                printLine();
                // process inputs
                String[] parts = input.split(" ");

                // error handling
                // error 1: no task specified
                if (parts.length < 2) {
                    System.out.println(" Please specify your task.");
                    continue;
                }

                // error 2: bad task specified
                int taskNumber = Integer.parseInt(parts[1]);
                if (taskNumber < 1 || taskNumber > counter) {
                    System.out.println(" Task does not exist.");
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
                printLine();
            }

            // add
            else {
                printLine();
                System.out.println("added: " + input);
                store[counter] = new Task(input);
                counter++;
                printLine();
            }
        }

        // shutdown protocols
        sc.close();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
