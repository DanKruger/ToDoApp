package org.todoer.client.commands.subcommands.todos;

import java.sql.SQLException;
import java.text.MessageFormat;

import org.todoer.client.ClientInterface;
import org.todoer.client.commands.Command;
import org.todoer.database.models.Todo;
import org.todoer.main.App;

/**
 * CreateTodo
 */
public class CreateTodo extends Command {

    public CreateTodo() {
        super("create", "creates a new Todo");
    }

    public boolean execute() {
        Todo todo;
        do {
            final String titleInput = ClientInterface.getInput("Please choose a title: ");
            final String descriptionInput = ClientInterface.getInput("Please choose a description: ");

            final String title = !titleInput.isEmpty() ? titleInput : "Untitled Note";
            final String description = !descriptionInput.isEmpty() ? descriptionInput : "No description";

            todo = new Todo(title, description);
        } while (!isAlright(todo));
        try {
            App.getServer().getDb().createTodo(todo);
            // new ListNotes().drawBox(todo);
            return true;
        } catch (final SQLException e) {
            throw new RuntimeException("Error creating note", e);
        }
    }

    private boolean isAlright(final Todo todo) {
        final String prompt = MessageFormat.format("New Note:\n{0}\n{1}\nIs this alright?\n(Y/n):", todo.getTitle(),
                todo.getContent());
        final String input = ClientInterface.getInput(prompt);
        return input.equals("Y") || input.equals("y");
    }

}
