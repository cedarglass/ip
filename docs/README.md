# Callie User Guide

Callie is a friendly task-tracking chatbot with a simple command format and a clean GUI.
Use it to track todos, deadlines, and events, then search, mark, or delete tasks quickly.

## Features

:information_source: **Notes about the command format**:

- Words in UPPER_CASE are the parameters to be supplied by the user.
  e.g. in `todo TASK_NAME`, `TASK_NAME` is a parameter which can be used as `todo read book`.
- Items in square brackets are optional.
- Items with `...` after them can be used multiple times.
- Parameters must follow the command keywords shown in the examples.
- Extraneous parameters for commands that do not take in parameters will be ignored.
  e.g. `list extra` is interpreted as `list`.

### Add a todo

Adds a simple todo task.

Format: `todo TASK_NAME`

### Add a deadline

Adds a deadline with a date or date-time.

Format: `deadline TASK_NAME by DATE_TIME`

- `DATE_TIME` can be `yyyy-mm-dd` or `yyyy-mm-dd HH:mm`.

### Add an event

Adds an event with a start and end date-time.

Format: `event TASK_NAME from START_DATE_TIME to END_DATE_TIME`

- `START_DATE_TIME` / `END_DATE_TIME` can be `yyyy-mm-dd` or `yyyy-mm-dd HH:mm`.

### List tasks

Shows all tasks.

Format: `list`

### Mark tasks as done (bulk)

Marks one or more tasks as done.

Format: `mark INDEX [MORE_INDEXES...]`

Supports:
- Spaces: `mark 1 2 3`
- Commas: `mark 1,2,3`
- Ranges: `mark 1-3`

### Unmark tasks (bulk)

Marks one or more tasks as not done.

Format: `unmark INDEX [MORE_INDEXES...]`

Supports:
- Spaces: `unmark 1 2 3`
- Commas: `unmark 1,2,3`
- Ranges: `unmark 1-3`

### Delete tasks (bulk)

Deletes one or more tasks.

Format: `delete INDEX [MORE_INDEXES...]`

Supports:
- Spaces: `delete 1 2 3`
- Commas: `delete 1,2,3`
- Ranges: `delete 1-3`

### Clear all tasks

Deletes all tasks.

Format: `clear`

### Find tasks

Finds tasks containing a keyword in the description.

Format: `find KEYWORD`

### Exit

Closes the app.

Format: `bye`
