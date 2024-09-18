package org.todoer.client.commands.subcommands.notes;

import java.sql.SQLException;

import org.todoer.client.commands.Command;
import org.todoer.database.models.Note;
import org.todoer.main.App;

public class ReadNote extends Command {
    private long id;

    public ReadNote(long id) {
        super("read", "Retrieves the note with the specified ID", TYPE.NOTES);
        this.id = id;
    }

    public boolean execute() {
        try {
            Note note = App.getServer().getDb().readNote(id);
            new ListNotes().drawBox(note);
        } catch (NullPointerException | SQLException e) {
            System.out.println("Could not find a note with that ID");
        }

        return false;
    }
}
