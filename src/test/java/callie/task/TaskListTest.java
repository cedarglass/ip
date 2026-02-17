package callie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addRemoveClear_updatesList() {
        TaskList list = new TaskList(new ArrayList<>());
        ToDo todo = new ToDo("read book");
        list.addTask(todo);
        assertEquals(1, list.getSize());
        assertSame(todo, list.getTask(0));

        Task removed = list.removeTask(0);
        assertSame(todo, removed);
        assertEquals(0, list.getSize());

        list.addTask(new ToDo("a"));
        list.addTask(new ToDo("b"));
        list.clearTasks();
        assertEquals(0, list.getSize());
    }

    @Test
    public void getTasks_returnsBackingList() {
        ArrayList<Task> backing = new ArrayList<>();
        TaskList list = new TaskList(backing);
        assertSame(backing, list.getTasks());
    }

    @Test
    public void addTask_duplicateTodo_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new ToDo("read book"));
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> list.addTask(new ToDo("read book")));
        assertEquals(" wait... this task already exists!", error.getMessage());
    }

    @Test
    public void addTask_duplicateDeadline_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        Deadline first = new Deadline("return book", java.time.LocalDateTime.parse("2019-10-15T09:30"));
        Deadline duplicate = new Deadline("return book", java.time.LocalDateTime.parse("2019-10-15T09:30"));
        list.addTask(first);
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> list.addTask(duplicate));
        assertEquals(" wait... this task already exists!", error.getMessage());
    }

    @Test
    public void addTask_duplicateEvent_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        Event first = new Event("meeting", java.time.LocalDateTime.parse("2019-10-16T10:00"),
                java.time.LocalDateTime.parse("2019-10-16T11:00"));
        Event duplicate = new Event("meeting", java.time.LocalDateTime.parse("2019-10-16T10:00"),
                java.time.LocalDateTime.parse("2019-10-16T11:00"));
        list.addTask(first);
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> list.addTask(duplicate));
        assertEquals(" wait... this task already exists!", error.getMessage());
    }

    @Test
    public void toString_printsEachTaskOnNewLine() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new ToDo("a"));
        list.addTask(new ToDo("b"));
        String text = list.toString();
        assertEquals("[T] [ ] a" + System.lineSeparator() + "[T] [ ] b" + System.lineSeparator(), text);
    }
}
