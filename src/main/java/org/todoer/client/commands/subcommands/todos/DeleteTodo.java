package org.todoer.client.commands.subcommands.todos;

import java.sql.SQLException;

import org.todoer.client.ClientInterface;
import org.todoer.client.commands.Command;
import org.todoer.database.DatabaseManager;
import org.todoer.database.models.Todo;
import org.todoer.main.App;

/**
 * DeleteTodo
 */
public class DeleteTodo extends Command {
    private final long id;

    public DeleteTodo(final long id) {
        super("delete", "deletes a todo from the database");
        this.id = id;
    }

    public boolean execute() {
        try {
            final DatabaseManager db = App.getServer().getDb();
            final Todo todoFromDb = db.readTodo(id);
            new ReadTodo(id).execute(); // Print the todo
            final String confirm = ClientInterface.getInput("Are you sure you want to delete this todo? (y/N)");
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Deletion Cancelled");
                return false;
            } else {
                db.deleteTodo(todoFromDb);
                return true;
            }
        } catch (NullPointerException | SQLException e) {
            System.out.println("Could not find a todo with that ID");
        }
        return false;
    }

}
