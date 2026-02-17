package callie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

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
        Deadline deadline = new Deadline("return book", LocalDateTime.parse("2019-10-15T09:30"));
        assertEquals("2019-10-15T09:30", deadline.getDeadline());
        assertEquals(LocalDateTime.parse("2019-10-15T09:30"), deadline.getDeadlineDate());
        assertEquals("[D] [ ] return book (by: Oct 15 2019 09:30)", deadline.toString());

        Event event = new Event("meeting", LocalDateTime.parse("2019-10-16T10:00"),
                LocalDateTime.parse("2019-10-17T12:15"));
        assertEquals("2019-10-16T10:00", event.getStart());
        assertEquals("2019-10-17T12:15", event.getEnd());
        assertEquals(LocalDateTime.parse("2019-10-16T10:00"), event.getStartDate());
        assertEquals(LocalDateTime.parse("2019-10-17T12:15"), event.getEndDate());
        assertEquals("[E] [ ] meeting (from: Oct 16 2019 10:00 to: Oct 17 2019 12:15)", event.toString());
    }

    @Test
    public void event_formatDates() {
        Event event = new Event("meeting", LocalDateTime.parse("2019-10-16T08:00"),
                LocalDateTime.parse("2019-10-17T17:00"));
        assertEquals("2019-10-16T08:00", event.getStart());
        assertEquals("2019-10-17T17:00", event.getEnd());
        assertEquals(LocalDateTime.parse("2019-10-16T08:00"), event.getStartDate());
        assertEquals(LocalDateTime.parse("2019-10-17T17:00"), event.getEndDate());
        assertEquals("[E] [ ] meeting (from: Oct 16 2019 08:00 to: Oct 17 2019 17:00)", event.toString());
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
