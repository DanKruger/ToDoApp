package org.todoer.client.commands.subcommands;

import org.todoer.client.commands.Command;

/**
 * HelpUser
 */
public class HelpUser extends Command {
    public HelpUser() {
        super("help", "Displpays helpful information to the console");
    }

    @Override
    public String toString() {
        return "HelpUser []";
    }

    public boolean execute() {
        final String helpMessage = """
                Usage: todoer <command> [options]

                Commands:
                  create                Create a new note in the database.
                  update <id>           Update the note with the specified id.
                  read <id>             Read the note with the specified id.
                  list                  List all notes in the database.
                  delete <id>           Delete the note with the specified id.

                Arguments:
                  <id>                  The ID of the note in the database (must be a number).

                Examples:
                  todoer create
                  todoer update 3
                  todoer read 1
                  todoer list
                  todoer delete 5

                Use 'todoer <command>' for specific command details.
                """;

        System.out.println(helpMessage);
        return true;
    }
}
