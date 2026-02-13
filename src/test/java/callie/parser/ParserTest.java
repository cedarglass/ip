package callie.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import callie.command.AddDeadlineCommand;
import callie.command.AddTodoCommand;
import callie.command.BulkDeleteCommand;
import callie.command.BulkMarkCommand;
import callie.command.BulkUnmarkCommand;
import callie.command.ClearCommand;

public class ParserTest {
    @Test
    public void parse_validCommands_returnsCommandTypes() {
        assertInstanceOf(AddTodoCommand.class, Parser.parse("todo read book"));
        assertInstanceOf(AddDeadlineCommand.class, Parser.parse("deadline return book by 2019-12-01"));
        assertInstanceOf(BulkMarkCommand.class, Parser.parse("mark 1"));
        assertInstanceOf(BulkUnmarkCommand.class, Parser.parse("unmark 2"));
        assertInstanceOf(BulkDeleteCommand.class, Parser.parse("delete 3"));
        assertInstanceOf(ClearCommand.class, Parser.parse("clear"));
    }

    @Test
    public void parse_invalidCommands_throws() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("deadline read book"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("event meeting from 2019-12-01"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("todo"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("find"));
        assertThrows(IllegalArgumentException.class, () -> Parser.parse("nonsense"));
    }
}
