# Callie User Guide

Welcome to Callie — a friendly, lightweight task tracker with a clean GUI.
Add todos, deadlines, and events, then find, mark, or delete tasks in seconds.

## Quick Start

Try these commands in order:

1. `todo read book`
2. `deadline return book by 2019-12-01 09:30`
3. `event project meeting from 2019-12-01 10:00 to 2019-12-01 12:00`
4. `list`
5. `find book`
6. `mark 1`
7. `bye`

```text
Expected flow: add → list → search → mark → exit
```

## Contents

- [Features](#features)
  - [Command summary](#command-summary)
  - [Add a todo](#add-a-todo)
  - [Add a deadline](#add-a-deadline)
  - [Add an event](#add-an-event)
  - [List tasks](#list-tasks)
  - [Mark tasks as done (bulk)](#mark-tasks-as-done-bulk)
  - [Unmark tasks (bulk)](#unmark-tasks-bulk)
  - [Delete tasks (bulk)](#delete-tasks-bulk)
  - [Clear all tasks](#clear-all-tasks)
  - [Find tasks](#find-tasks)
  - [Exit](#exit)

## Features

> ℹ️ Notes about the command format
>
> - Words in UPPER_CASE are the parameters to be supplied by the user.
>   e.g. in `todo TASK_NAME`, `TASK_NAME` is a parameter which can be used as `todo read book`.
> - Items in square brackets are optional.
> - Items with `...` after them can be used multiple times.
> - Parameters must follow the command keywords shown in the examples.
> - Extraneous parameters for commands that do not take in parameters will be ignored.
>   e.g. `list extra` is interpreted as `list`.
>
> Tip: dates support both `yyyy-mm-dd` and `yyyy-mm-dd HH:mm`.

### Command summary

| Action | Format | Example |
| --- | --- | --- |
| Add todo | `todo TASK_NAME` | `todo read book` |
| Add deadline | `deadline TASK_NAME by DATE_TIME` | `deadline return book by 2019-12-01 09:30` |
| Add event | `event TASK_NAME from START to END` | `event meeting from 2019-12-01 10:00 to 2019-12-01 12:00` |
| List | `list` | `list` |
| Mark (bulk) | `mark INDEX [MORE...]` | `mark 1 2 3` |
| Unmark (bulk) | `unmark INDEX [MORE...]` | `unmark 1,2,3` |
| Delete (bulk) | `delete INDEX [MORE...]` | `delete 1-3` |
| Clear | `clear` | `clear` |
| Find | `find KEYWORD` | `find book` |
| Exit | `bye` | `bye` |

### Add a todo

Adds a simple todo task.

Format: `todo TASK_NAME`

Example:
```text
todo read book
```

### Add a deadline

Adds a deadline with a date or date-time.

Format: `deadline TASK_NAME by DATE_TIME`

- `DATE_TIME` can be `yyyy-mm-dd` or `yyyy-mm-dd HH:mm`.

Example:
```text
deadline return book by 2019-12-01 09:30
```

### Add an event

Adds an event with a start and end date-time.

Format: `event TASK_NAME from START_DATE_TIME to END_DATE_TIME`

- `START_DATE_TIME` / `END_DATE_TIME` can be `yyyy-mm-dd` or `yyyy-mm-dd HH:mm`.

Example:
```text
event project meeting from 2019-12-01 10:00 to 2019-12-01 12:00
```

### List tasks

Shows all tasks.

Format: `list`

### Mark tasks as done (bulk)

Marks one or more tasks as done.

Format: `mark INDEX [MORE_INDEXES...]`

| Format | Example |
| --- | --- |
| Spaces | `mark 1 2 3` |
| Commas | `mark 1,2,3` |
| Ranges | `mark 1-3` |

### Unmark tasks (bulk)

Marks one or more tasks as not done.

Format: `unmark INDEX [MORE_INDEXES...]`

| Format | Example |
| --- | --- |
| Spaces | `unmark 1 2 3` |
| Commas | `unmark 1,2,3` |
| Ranges | `unmark 1-3` |

### Delete tasks (bulk)

Deletes one or more tasks.

Format: `delete INDEX [MORE_INDEXES...]`

| Format | Example |
| --- | --- |
| Spaces | `delete 1 2 3` |
| Commas | `delete 1,2,3` |
| Ranges | `delete 1-3` |

### Clear all tasks

Deletes all tasks.

Format: `clear`

### Find tasks

Finds tasks containing a keyword in the description.

Format: `find KEYWORD`

Example:
```text
find book
```

### Exit

Closes the app.

Format: `bye`
