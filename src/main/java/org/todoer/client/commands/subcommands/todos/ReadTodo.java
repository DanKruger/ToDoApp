package org.todoer.client.commands.subcommands.todos;

import org.todoer.client.commands.Command;
import org.todoer.database.models.Todo;
import org.todoer.main.App;

import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * ReadTodo
 */
public class ReadTodo extends Command {
    private final long id;

    public ReadTodo(long id) {
        super("read", "Read the information of a todo item",TYPE.TODOS);
        this.id = id;

    }

    public boolean execute() {
        try {
            Todo todo = App.getServer().getDb().readTodo(id);
            System.out.println(
                    MessageFormat.format("ID: {0}\nStatus: {3}\n\nT: {1}\n\nD: {2}\n",
                            todo.getId(), todo.getTitle(), todo.getContent(), todo.isComplete() ? "Done" : "To Do"));
            return true;
        } catch (NullPointerException | SQLException e) {
            System.out.println("Could not find a todo with that ID");
            return false;
        }
    }

}
