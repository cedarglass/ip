package callie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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
    public void toString_printsEachTaskOnNewLine() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new ToDo("a"));
        list.addTask(new ToDo("b"));
        String text = list.toString();
        assertEquals("[T] [ ] a" + System.lineSeparator() + "[T] [ ] b" + System.lineSeparator(), text);
    }
}
