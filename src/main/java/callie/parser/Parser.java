package callie.parser;

import java.time.LocalDate;

import callie.command.AddDeadlineCommand;
import callie.command.AddEventCommand;
import callie.command.AddTodoCommand;
import callie.command.ByeCommand;
import callie.command.ClearCommand;
import callie.command.Command;
import callie.command.DeleteCommand;
import callie.command.FindCommand;
import callie.command.ListCommand;
import callie.command.MarkCommand;
import callie.command.UnmarkCommand;


/**
 * Parses user input into command objects.
 */
public class Parser {
    private static final String MISSING_MESSAGE = " umm... you need to specify your task.";

    /**
     * Parses the given input by extracting any necessary strings or indices.
     * Then, feeds them into a command.
     *
     * @param input The raw user input.
     * @return The corresponding command.
     */
    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ByeCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        if (input.equals("clear")) {
            return new ClearCommand();
        }
        if (input.startsWith("mark")) {
            int index = parseIndex(input, MISSING_MESSAGE);
            return new MarkCommand(index);
        }
        if (input.startsWith("unmark")) {
            int index = parseIndex(input, MISSING_MESSAGE);
            return new UnmarkCommand(index);
        }
        if (input.startsWith("delete")) {
            int index = parseIndex(input, MISSING_MESSAGE);
            return new DeleteCommand(index);
        }
        if (input.startsWith("todo")) {
            String name = parseTodoName(input, MISSING_MESSAGE);
            return new AddTodoCommand(name);
        }
        if (input.startsWith("deadline")) {
            String[] parts = parseDeadline(input);
            LocalDate date = parseDate(parts[1]);
            return new AddDeadlineCommand(parts[0], date);
        }
        if (input.startsWith("event")) {
            String[] parts = parseEvent(input);
            LocalDate start = parseDate(parts[1]);
            LocalDate end = parseDate(parts[2]);
            return new AddEventCommand(parts[0], start, end);
        }
        if (input.startsWith("find")) {
            String searchString = parseSearchString(input);
            return new FindCommand(searchString);
        }
        throw new IllegalArgumentException(MISSING_MESSAGE);
    }

    /**
     * Parses the task index from a command.
     *
     * @param input The raw user input.
     * @param missingMessage Error message when index is missing.
     * @return The parsed one-based index.
     */
    private static int parseIndex(String input, String missingMessage) {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new IllegalArgumentException(missingMessage);
        }
        try {
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("wait, your task doesn't exist!");
        }
    }

    /**
     * Parses the task name from a todo command.
     *
     * @param input The raw user input.
     * @return The task name.
     */
    private static String parseTodoName(String input, String missingMessage) {
        String name = input.substring(4).trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException(missingMessage);
        }
        return name;
    }

    /**
     * Parses a deadline command into task name and date text.
     *
     * @param input The raw user input.
     * @return An array of [name, dateText].
     */
    private static String[] parseDeadline(String input) {
        String[] parts = input.split(" by ");
        if (parts.length < 2) {
            throw new IllegalArgumentException(" Please specify your task with the word 'by'.");
        }
        String name = parts[0].substring(9).trim();
        String dateText = parts[1].trim();
        return new String[] { name, dateText };
    }

    /**
     * Parses an event command into task name, start date text, and end date text.
     *
     * @param input The raw user input.
     * @return An array of [name, startDateText, endDateText].
     */
    private static String[] parseEvent(String input) {
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

    /**
     * Parses an ISO date (yyyy-mm-dd) into a LocalDate.
     *
     * @param dateText The date text to parse.
     * @return The parsed LocalDate.
     */
    private static LocalDate parseDate(String dateText) {
        try {
            return LocalDate.parse(dateText);
        } catch (Exception e) {
            throw new IllegalArgumentException(" Please use date format yyyy-mm-dd.");
        }
    }

    private static String parseSearchString(String input) {
        String name = input.substring(4).trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("umm... you need to specify a search string.");
        }
        return name;
    }
}
