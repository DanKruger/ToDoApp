package org.todoer.client.commands.subcommands.todos;

import java.sql.SQLException;

import org.todoer.client.commands.Command;
import org.todoer.database.models.Todo;
import org.todoer.main.App;

/**
 * ListTodos
 */
public class ListTodos extends Command {

    public ListTodos() {
        super("list", "list all todos");

    }

    public boolean execute() {
        try {
            final var data = App.getServer().getDb().getAllTodos();
            for (final Todo todo : data) {
                System.out.println(todo.format());
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
