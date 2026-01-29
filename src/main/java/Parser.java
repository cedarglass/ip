/**
 * Parses user input into command components.
 */
public class Parser {
    public boolean isBye(String input) {
        return input.equals("bye");
    }

    public boolean isList(String input) {
        return input.equals("list");
    }

    public boolean isClear(String input) {
        return input.equals("clear");
    }

    public boolean isMark(String input) {
        return input.startsWith("mark");
    }

    public boolean isUnmark(String input) {
        return input.startsWith("unmark");
    }

    public boolean isDelete(String input) {
        return input.startsWith("delete");
    }

    public boolean isTodo(String input) {
        return input.startsWith("todo");
    }

    public boolean isDeadline(String input) {
        return input.startsWith("deadline");
    }

    public boolean isEvent(String input) {
        return input.startsWith("event");
    }

    public int parseIndex(String input, String missingMessage) {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new IllegalArgumentException(missingMessage);
        }
        return Integer.parseInt(parts[1]);
    }

    public String parseTodoName(String input) {
        String name = input.substring(4).trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("umm... you need to specify a task name.");
        }
        return name;
    }

    public String[] parseDeadline(String input) {
        String[] parts = input.split(" by ");
        if (parts.length < 2) {
            throw new IllegalArgumentException(" Please specify your task with the word 'by'.");
        }
        String name = parts[0].substring(9).trim();
        String dateText = parts[1].trim();
        return new String[] { name, dateText };
    }

    public String[] parseEvent(String input) {
        String[] parts = input.split(" from ");
        if (parts.length < 2) {
            throw new IllegalArgumentException(" Please specify your task with the word 'from' and 'to'.");
        }
        String[] dates = parts[1].split(" to ");
        if (dates.length < 2) {
            throw new IllegalArgumentException(" Please specify your task with the word 'from' and 'to'.");
        }
        String name = parts[0].substring(6).trim();
        return new String[] { name, dates[0].trim(), dates[1].trim() };
    }
}
