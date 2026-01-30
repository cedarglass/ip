package callie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void todo_markAndReset_updatesStatus() {
        ToDo todo = new ToDo("read book");
        assertFalse(todo.isDone());
        assertEquals("[T] [ ] read book", todo.toString());

        todo.done();
        assertTrue(todo.isDone());
        assertEquals("[T] [X] read book", todo.toString());

        todo.reset();
        assertFalse(todo.isDone());
        assertEquals("[T] [ ] read book", todo.toString());
    }

    @Test
    public void deadline_formatDates() {
        Deadline deadline = new Deadline("return book", LocalDate.parse("2019-10-15"));
        assertEquals("2019-10-15", deadline.getDeadline());
        assertEquals(LocalDate.parse("2019-10-15"), deadline.getDeadlineDate());
        assertEquals("[D] [ ] return book (by: Oct 15 2019)", deadline.toString());

        Event event = new Event("meeting", LocalDate.parse("2019-10-16"), LocalDate.parse("2019-10-17"));
        assertEquals("2019-10-16", event.getStart());
        assertEquals("2019-10-17", event.getEnd());
        assertEquals(LocalDate.parse("2019-10-16"), event.getStartDate());
        assertEquals(LocalDate.parse("2019-10-17"), event.getEndDate());
        assertEquals("[E] [ ] meeting (from: Oct 16 2019 to: Oct 17 2019)", event.toString());
    }

    @Test
    public void event_formatDates() {
        Event event = new Event("meeting", LocalDate.parse("2019-10-16"), LocalDate.parse("2019-10-17"));
        assertEquals("2019-10-16", event.getStart());
        assertEquals("2019-10-17", event.getEnd());
        assertEquals(LocalDate.parse("2019-10-16"), event.getStartDate());
        assertEquals(LocalDate.parse("2019-10-17"), event.getEndDate());
        assertEquals("[E] [ ] meeting (from: Oct 16 2019 to: Oct 17 2019)", event.toString());
    }

    @Test
    public void task_setDone_updatesStatus() {
        ToDo todo = new ToDo("wash clothes");
        todo.setDone(true);
        assertTrue(todo.isDone());
        todo.setDone(false);
        assertFalse(todo.isDone());
        assertEquals("wash clothes", todo.getName());
    }
}
