package org.todoer.client.commands.subcommands;

import java.sql.SQLException;
import java.text.MessageFormat;

import org.todoer.client.ClientInterface;
import org.todoer.client.commands.Command;
import org.todoer.database.models.Note;
import org.todoer.main.App;

public class CreateNote extends Command {
    public CreateNote() {
        super("create", "Create a note");
    }

    @Override
    public String toString() {
        return "CreateNote []";
    }

    @Override
    public boolean execute() {
        Note note;
        do {
            final String titleInput = ClientInterface.getInput("Please choose a title: ");
            final String descriptionInput = ClientInterface.getInput("Please choose a description: ");

            final String title = !titleInput.isEmpty() ? titleInput : "Untitled Note";
            final String description = !descriptionInput.isEmpty() ? descriptionInput : "No description";

            note = new Note(title, description);
        } while (!isAlright(note));
        try {
            App.getServer().getDb().createNote(note);
            new ListNotes().drawBox(note);
            return true;
        } catch (final SQLException e) {
            throw new RuntimeException("Error creating note", e);
        }
    }

    private boolean isAlright(final Note note) {
        final String prompt = MessageFormat.format("New Note:\n{0}\n{1}\nIs this alright?\n(Y/n):", note.getTitle(),
                note.getContent());
        final String input = ClientInterface.getInput(prompt);
        return input.equals("Y") || input.equals("y");
    }
}
