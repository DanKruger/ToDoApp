package org.todoer.client.commands.subcommands.notes;

import java.sql.SQLException;

import org.todoer.client.ClientInterface;
import org.todoer.client.commands.Command;
import org.todoer.database.models.Note;
import org.todoer.main.App;

public class DeleteNote extends Command {
    private final long id;

    public DeleteNote(final long id) {
        super("delete", "remove a note from the database");
        this.id = id;
    }

    public boolean execute() {
        try {
            final Note noteFromDb = App.getServer().getDb().readNote(id);
            new ListNotes().drawBox(noteFromDb);
            final String confirm = ClientInterface.getInput("Are you sure you want to delete this note? (y/N): ");
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Deletion Cancelled");
                return false;
            } else {
                App.getServer().getDb().deleteNote(noteFromDb);
                System.out.println("Note deleted");
            }
        } catch (NullPointerException | SQLException e) {
            System.out.println("Could not find a note with that ID");
        }
        return false;
    }
}
