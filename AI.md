# AI Work Log

## Overview
I used Codex with GPT-5 for all my edits, persisting it within the same 
conversation to take advantage of the 400k-token context window. 
This allows GPT-5 to maintain knowledge of my codebase and avoid making
conflicting edits. 

# AI-assisted increments:
## Level-7: Save, Level-8: Dates and Times, Level-9: Find 
- Work done:
  - Added storage persistence and data loading as a separate Storage.java class.
  - Switched dates to `LocalDate` objects and formatted outputs.
  - Added `find` support and updated related tests.
- Notes: Iterative added one Level at the time to keep edits to core classes and tests simple.

## A-MoreOOP
- Work done:
  - Extracted the commands handling what the user sees as the`Ui` class.
  - Added `Parser` and `TaskList` classes and updated call sites.
  - Introduced command classes and refactored main loop to use them.
- Notes: 
  - AI was able to give several good suggestions on design choices: what to abstract to keep the software architecture clean.
  - AI was able to track the call sites to change for new classes pretty accurately – better than me.

## A-Packaging 
- Work done:
  - Reorganized Java classes into packages under `callie.*`.
- Notes: This is a labour-intensive task I was glad to let AI handle. It saved me ~15 minutes.

## A-JUnit
- Work done: Generated basic tests, creating a template I could build on.
- Notes: AI is good for writing basic tests, but edge cases are better caught by the programmer.

## Level-10: GUI
- Work done:
  - Added JavaFX `MainWindow` and `DialogBox` controllers and FXML.
  - Added a GUI-oriented logic layer and wiring in `MainApp`.
- Notes: AI's suggestion to add a Logic layer is something I would not have thought of here. Kudos!

## A-CodeQuality, A-Assertions
- Work done:
  - Added runtime assertions to document assumptions.
  - Refactored for readability (reduced static state, normalized search handling).
- Notes: I made AI annotate any code quality changes with rule citations, so I could understand what violations it caught..

## BCD-Extensions: Bulk Operations
- Work done:
  - Added bulk `mark/unmark/delete` commands and multi-index parsing.
  - Updated parser and tests to use bulk commands.
- Notes: Mostly a parsing change.

## A-BetterGui
- Work done: 
  - Added spacing for dialog boxes, colours, mild elevation of boxes.
- Notes: GPT-5 is less able to understand FXML code relative to normal code, and trips up more.

## Observations
- Most of the time saved comes from multi-file refactors and repetitive edits. Probably ~1.5 hours was saved.
- AI is good at designing, but it is probably wiser to discuss with it before implementing those design changes.
- Some of these levels have learning outcomes related to design, and I should have done them more thoroughly
before consulting AI (e.g. Save, MoreOOP, GUI). 
- AI is good at checking and flagging errors in a justifiable manner.
- Codex provides all the diffs it makes for every edit, so I feel up to date with code changes even while using it. 
- 
