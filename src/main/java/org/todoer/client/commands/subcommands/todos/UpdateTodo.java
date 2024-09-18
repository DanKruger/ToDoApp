package org.todoer.client.commands.subcommands.todos;

import java.sql.SQLException;

import org.todoer.client.ClientInterface;
import org.todoer.client.commands.Command;
import org.todoer.client.commands.subcommands.notes.ListNotes;
import org.todoer.database.models.Note;
import org.todoer.database.models.Todo;
import org.todoer.main.App;

/**
 * UpdateTodo
 */
public class UpdateTodo extends Command {
    private final long id;

    public UpdateTodo(long id) {
        super("update", "update a todo");
        this.id = id;
    }

    public boolean execute() {
        try {
            final Todo todoFromDb = App.getServer().getDb().readTodo(id);
            final String change = ClientInterface
                    .getInput("What would you like to edit?\n0: Toggle Done\n1: Title\n2: Description\n3: Cancel\n");
            if (change.equalsIgnoreCase("3"))
                return false;
            if (change.equalsIgnoreCase("0")) {
                boolean done = todoFromDb.isComplete() ? false : true;
                todoFromDb.setComplete(done);
            }
            if (change.equalsIgnoreCase("1")) {
                final String newTitle = ClientInterface.getInput("Enter new Title: ");
                todoFromDb.setTitle(newTitle.isEmpty() ? "Untitled" : newTitle);
                System.out.println("New Title: " + todoFromDb.getTitle());
            }
            if (change.equalsIgnoreCase("2")) {
                final String newDesc = ClientInterface.getInput("Enter new Description: ");
                todoFromDb.setContent(newDesc.isEmpty() ? "Empty" : newDesc);
                System.out.println("New Description:\n " + todoFromDb.getContent());
            }
            App.getServer().getDb().updateTodo(todoFromDb);
            new ReadTodo(id).execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
