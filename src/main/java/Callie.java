import java.util.Scanner;

public class Callie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printLine();
        System.out.println("Hello, I'm Callie! It's great to see you around today.\nWhat can I do for you?\n");
        printLine();

        String[] store = new String[100];
        int counter = 0;

        // repeat what the user types in (i.e. the input), except for when "bye" is typed
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            if (input.equals("list")) {
                printLine();
                for (int i = 0; i < counter; i++) {
                    int j = i + 1;
                    System.out.println(j + ". " + store[i]);
                }
                printLine();
            }

            else {
                printLine();
                System.out.println("added: " + input);
                store[counter] = input;
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
