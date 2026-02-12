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
        assert input != null : "Input string for parsing should not be null.";
        String trimmed = input.trim();
        String[] parts = trimmed.split(" ", 2);
        String keyword = parts[0];
        String rest = parts.length > 1 ? parts[1].trim() : "";

        switch (keyword) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "clear":
            return new ClearCommand();
        case "mark":
            return new MarkCommand(parseIndex(rest, MISSING_MESSAGE));
        case "unmark":
            return new UnmarkCommand(parseIndex(rest, MISSING_MESSAGE));
        case "delete":
            return new DeleteCommand(parseIndex(rest, MISSING_MESSAGE));
        case "todo":
            return new AddTodoCommand(parseTodoName(rest, MISSING_MESSAGE));
        case "deadline": {
            String[] deadlineParts = parseDeadline(rest);
            LocalDate date = parseDate(deadlineParts[1]);
            return new AddDeadlineCommand(deadlineParts[0], date);
        }
        case "event": {
            String[] eventParts = parseEvent(rest);
            LocalDate start = parseDate(eventParts[1]);
            LocalDate end = parseDate(eventParts[2]);
            return new AddEventCommand(eventParts[0], start, end);
        }
        case "find":
            return new FindCommand(parseSearchString(rest));
        default:
            throw new IllegalArgumentException(MISSING_MESSAGE);
        }
    }

    /**
     * Parses the task index from the command payload.
     *
     * @param rest The text after the command keyword.
     * @param missingMessage Error message when index is missing.
     * @return The parsed one-based index.
     */
    private static int parseIndex(String rest, String missingMessage) {
        if (rest.isEmpty()) {
            throw new IllegalArgumentException(missingMessage);
        }
        try {
            return Integer.parseInt(rest);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("wait, your task doesn't exist!");
        }
    }

    /**
     * Parses the task name from a todo command payload.
     *
     * @param rest The text after the command keyword.
     * @param missingMessage Error message when index is missing.
     * @return The task name.
     */
    private static String parseTodoName(String rest, String missingMessage) {
        if (rest.isEmpty()) {
            throw new IllegalArgumentException(missingMessage);
        }
        return rest;
    }

    /**
     * Parses a deadline command payload into task name and date text.
     *
     * @param rest The text after the command keyword.
     * @return An array of [name, dateText].
     */
    private static String[] parseDeadline(String rest) {
        String[] parts = rest.split(" by ");
        if (parts.length < 2) {
            throw new IllegalArgumentException(" Please specify your task with the word 'by'.");
        }
        String name = parts[0].trim();
        String dateText = parts[1].trim();
        return new String[] { name, dateText };
    }

    /**
     * Parses an event command payload into task name, start date text, and end date text.
     *
     * @param rest The text after the command keyword.
     * @return An array of [name, startDateText, endDateText].
     */
    private static String[] parseEvent(String rest) {
        String[] parts = rest.split(" from ");
        if (parts.length < 2) {
            throw new IllegalArgumentException(" Please specify your task with the word 'from' and 'to'.");
        }
        String[] dates = parts[1].split(" to ");
        if (dates.length < 2) {
            throw new IllegalArgumentException(" Please specify your task with the word 'from' and 'to'.");
        }
        String name = parts[0].trim();
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

    /**
     * Parses the search string from a find command payload.
     *
     * @param rest The text after the command keyword.
     * @return The search string.
     */
    private static String parseSearchString(String rest) {
        if (rest.isEmpty()) {
            throw new IllegalArgumentException("umm... you need to specify a search string.");
        }
        return rest;
    }
}
