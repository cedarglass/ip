package callie.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import callie.command.AddDeadlineCommand;
import callie.command.AddEventCommand;
import callie.command.AddTodoCommand;
import callie.command.BulkDeleteCommand;
import callie.command.BulkMarkCommand;
import callie.command.BulkUnmarkCommand;
import callie.command.ByeCommand;
import callie.command.ClearCommand;
import callie.command.FindCommand;
import callie.command.ListCommand;

public class ParserTest {
    @Test
    public void parse_validCommands_returnsCommandTypes() {
        assertInstanceOf(ClearCommand.class, Parser.parse("clear"));
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
        assertInstanceOf(FindCommand.class, Parser.parse("find book"));
        assertInstanceOf(AddTodoCommand.class, Parser.parse("todo read book"));
        assertInstanceOf(AddDeadlineCommand.class, Parser.parse(
                "deadline return book by 2019-12-01 09:30"));
        assertInstanceOf(AddEventCommand.class, Parser.parse(
                "event meeting from 2019-12-01 10:00 to 2019-12-01 12:00"));
        assertInstanceOf(BulkMarkCommand.class, Parser.parse("mark 1"));
        assertInstanceOf(BulkUnmarkCommand.class, Parser.parse("unmark 2"));
        assertInstanceOf(BulkDeleteCommand.class, Parser.parse("delete 3"));
        assertInstanceOf(BulkMarkCommand.class, Parser.parse("mark 1,3 5-6"));
        assertInstanceOf(BulkUnmarkCommand.class, Parser.parse("unmark 2-4"));
        assertInstanceOf(BulkDeleteCommand.class, Parser.parse("delete 1,2,3"));
        assertInstanceOf(ByeCommand.class, Parser.parse("bye"));
    }

    @Test
    public void parse_invalidCommands_throwsWithMessages() {
        IllegalArgumentException deadlineError = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("deadline read book"));
        assertEquals(" Please specify your task with the word 'by'.", deadlineError.getMessage());

        IllegalArgumentException eventError = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("event meeting from 2019-12-01"));
        assertEquals(" Please specify your task with the word 'from' and 'to'.", eventError.getMessage());

        IllegalArgumentException todoError = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("todo"));
        assertEquals(" umm... you need to specify your task.", todoError.getMessage());

        IllegalArgumentException findError = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("find"));
        assertEquals("umm... you need to specify a search string.", findError.getMessage());

        IllegalArgumentException unknownError = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("nonsense"));
        assertEquals(" umm... you need to specify your task.", unknownError.getMessage());
    }

    @Test
    public void parse_invalidDateTime_throwsFormatMessage() {
        IllegalArgumentException dayError = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("deadline return book by 2019-12-40 10:00"));
        assertEquals(" Please use date format yyyy-mm-dd or yyyy-mm-dd HH:mm.", dayError.getMessage());

        IllegalArgumentException monthError = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("deadline return book by 2019-13-01 10:00"));
        assertEquals(" Please use date format yyyy-mm-dd or yyyy-mm-dd HH:mm.", monthError.getMessage());

        IllegalArgumentException timeError = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("deadline return book by 2019-12-40 10:60"));
        assertEquals(" Please use date format yyyy-mm-dd or yyyy-mm-dd HH:mm.", timeError.getMessage());
    }

    @Test
    public void parse_invalidIndices_throws() {
        IllegalArgumentException missingIndex = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("mark"));
        assertEquals(" umm... you need to specify your task.", missingIndex.getMessage());

        IllegalArgumentException badIndex = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("mark one"));
        assertEquals("wait, your task doesn't exist!", badIndex.getMessage());
    }

    @Test
    public void parse_bulkIndexFormats_acceptsSpacesCommasRanges() {
        assertInstanceOf(BulkMarkCommand.class, Parser.parse("mark 1 2 3"));
        assertInstanceOf(BulkUnmarkCommand.class, Parser.parse("unmark 1,2,3"));
        assertInstanceOf(BulkDeleteCommand.class, Parser.parse("delete 1-3"));
        assertInstanceOf(BulkDeleteCommand.class, Parser.parse("delete 1,3 5-6"));
        assertInstanceOf(BulkDeleteCommand.class, Parser.parse("delete 1, 2-4 6"));
        assertInstanceOf(BulkMarkCommand.class, Parser.parse("mark 1,1,2"));
    }

    @Test
    public void parse_invalidRanges_throws() {
        IllegalArgumentException reversedRange = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("delete 3-1"));
        assertEquals("wait, your task doesn't exist!", reversedRange.getMessage());

        IllegalArgumentException malformedRange = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("delete 1-2-3"));
        assertEquals("wait, your task doesn't exist!", malformedRange.getMessage());

        IllegalArgumentException missingUpper = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("delete 1-"));
        assertEquals("wait, your task doesn't exist!", missingUpper.getMessage());
    }

    @Test
    public void parse_deadlineMissingName_throws() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("deadline by 2019-01-01 10:00"));
        assertEquals(" Please specify your deadline name!", error.getMessage());
    }

    @Test
    public void parse_deadlineMissingDate_throws() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("deadline return book by "));
        assertEquals(" Please specify your deadline date!", error.getMessage());
    }

    @Test
    public void parse_eventMissingName_throws() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("event from 2019-01-01 to 2019-02-02"));
        assertEquals(" Please specify your event name!", error.getMessage());
    }

    @Test
    public void parse_eventMissingStartOrEnd_throws() {
        IllegalArgumentException missingStart = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("event meeting from  to 2019-01-01"));
        assertEquals(" Please specify your start and end times!", missingStart.getMessage());

        IllegalArgumentException missingEnd = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("event meeting from 2019-01-01 to "));
        assertEquals(" Please specify your start and end times!", missingEnd.getMessage());
    }

    @Test
    public void parse_eventEndBeforeStart_throws() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parse("event meeting from 2019-01-02 10:00 to 2019-01-02 09:00"));
        assertEquals(
                " Please ensure your start time comes before your end time chronologically!",
                error.getMessage()
        );
    }
}
