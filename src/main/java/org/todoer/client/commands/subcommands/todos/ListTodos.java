package org.todoer.client.commands.subcommands.todos;

import org.todoer.client.commands.Command;
import org.todoer.database.models.Todo;
import org.todoer.main.App;

import java.sql.SQLException;

/**
 * ListTodos
 */
public class ListTodos extends Command {

    public ListTodos() {
        super("list", "list all todos",TYPE.TODOS);

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
