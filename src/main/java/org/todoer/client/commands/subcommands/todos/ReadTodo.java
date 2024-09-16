package org.todoer.client.commands.subcommands.todos;

import java.sql.SQLException;
import java.text.MessageFormat;

import org.todoer.client.commands.Command;
import org.todoer.database.models.Todo;
import org.todoer.main.App;

/**
 * ReadTodo
 */
public class ReadTodo extends Command {
    private long id;

    public ReadTodo(long id) {
        super("read", "Read the information of a todo item");
        this.id = id;

    }

    public boolean execute() {
        try {
            Todo todo = App.getServer().getDb().readTodo(id);
            System.out.println(
                    MessageFormat.format("\nID: {0}\nStatus: {3}\n\nTitle:\n\n    {1}\n\nDescription:\n\n    {2}\n\n",
                            todo.getId(), todo.getTitle(), todo.getContent(), todo.isComplete() ? "Done" : "To Do"));
            return true;
        } catch (NullPointerException | SQLException e) {
            System.out.println("Could not find a todo with that ID");
            return false;
        }
    }

}
