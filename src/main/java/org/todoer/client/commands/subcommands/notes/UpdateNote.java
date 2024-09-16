package org.todoer.client.commands.subcommands.notes;

import java.sql.SQLException;

import org.todoer.client.ClientInterface;
import org.todoer.client.commands.Command;
import org.todoer.database.models.Note;
import org.todoer.main.App;

public class UpdateNote extends Command {
    private final long id;

    public UpdateNote(final long id) {
        super("update", "Update the note with the given id");
        this.id = id;
    }

    public boolean execute() {
        try {
            final Note noteFromDb = App.getServer().getDb().readNote(id);
            final String change = ClientInterface
                    .getInput("What would you like to edit?\n1: Title\n2: Description\n3: Cancel\n");
            if (change.equalsIgnoreCase("3"))
                return false;
            if (change.equalsIgnoreCase("1")) {
                final String newTitle = ClientInterface.getInput("Enter new Title: ");
                noteFromDb.setTitle(newTitle.isEmpty() ? "Untitled" : newTitle);
                System.out.println("New Title: " + noteFromDb.getTitle());
            }
            if (change.equalsIgnoreCase("2")) {
                final String newDesc = ClientInterface.getInput("Enter new Description: ");
                noteFromDb.setContent(newDesc.isEmpty() ? "Empty" : newDesc);
                System.out.println("New Description:\n " + noteFromDb.getContent());
            }
            new ListNotes().drawBox(noteFromDb);
            App.getServer().getDb().updateNote(noteFromDb);
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
