package org.todoer.client.commands.subcommands.notes;

import java.sql.SQLException;

import org.todoer.client.commands.Command;
import org.todoer.database.models.Note;
import org.todoer.main.App;

public class ListNotes extends Command {
    public ListNotes() {
        super("list", "List all notes in the database",TYPE.NOTES);
    }

    @Override
    public String toString() {
        return "ListNotes []";
    }

    public boolean execute() {
        try {
            final var data = App.getServer().getDb().getAllNotes();
            for (final Note note : data) {
                drawBox(note);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void drawBox(final Note note){
        final String title = note.getTitle();
        final String content = note.getContent();
        final long id = note.getId();

        final int titleLen= title.length();
        final int contentLen = content.length();
        final int idLen = String.valueOf(id).length();
        final int baseLen = 10;

        final int noteLen = Math.max(titleLen, contentLen) + 2;
        final int len = Math.max(baseLen, noteLen);

        final String titlePadding = getMissing(titleLen,len);
        final String contentPadding = getMissing(contentLen,len);

        String boxBuilder = " ┌ID" + "─".repeat(idLen) + "┐\n" +
                " │ " + id + " │\n" +
                "╔╧" + "═".repeat(idLen + 2) + "╧" + "═".repeat(len - (idLen + 4)) + "╗\n" +
                "║" + title + titlePadding + "║\n" +
                "╟" + "─".repeat(len) + "╢\n" +
                "║" + content + contentPadding + "║\n" +
                "╚" + "═".repeat(len) + "╝\n";

        // Output the box
        System.out.println(boxBuilder);
    }
    private String getMissing(final int one, final int two) {
        return one > two || one < two ? " ".repeat(getLen(one, two)) : "";
    }

    private int getLen(final int one, final int two) {
        if (one > two)
            return one - two;
        if (one < two)
            return two - one;
        return 0;
    }

}
