package org.todoer.client.commands.subcommands;

import java.sql.SQLException;

import org.todoer.client.commands.Command;
import org.todoer.database.models.Note;
import org.todoer.main.App;

public class ListNotes extends Command {
    public ListNotes() {
        super("list", "List all notes in the database");
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

    public void drawBox(final Note note) {
        final int descLen = note.getContent().length();
        final int headLen = note.getTitle().length();
        final int idlen = String.valueOf(note.getId()).length();
        final int baseLen = 10;
        final int noteLen = headLen > descLen ? headLen + 2 : descLen + 2;
        final int len = Math.max(baseLen, noteLen);
        final String headspace = getMissing(headLen, len);
        final String descspace = getMissing(descLen, len);
        System.out.println(" ┌ID" + "─".repeat(idlen) + "┐");
        System.out.println(" │ " + note.getId() + " │");
        System.out.println("╔╧" + "═".repeat(idlen + 2) + "╧" + "═".repeat(len - (idlen + 4)) + "╗");
        System.out.println("║" + note.getTitle() + headspace + "║");
        System.out.println("╟" + "─".repeat(len) + "╢");
        System.out.println("║" + note.getContent() + descspace + "║");
        System.out.println("╚" + "═".repeat(len) + "╝");
    }

    private String getMissing(final int one, final int two) {
        if (one > two)
            return " ".repeat(getLen(one, two));
        if (one < two)
            return " ".repeat(getLen(one, two));
        return "";
    }

    private int getLen(final int one, final int two) {
        if (one > two)
            return one - two;
        if (one < two)
            return two - one;
        return 0;
    }

}
