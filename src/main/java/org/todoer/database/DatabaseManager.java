package org.todoer.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.todoer.database.models.Note;
import org.todoer.database.schemas.NoteSchema;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.logger.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.table.TableUtils;

public class DatabaseManager {
    private static JdbcPooledConnectionSource conn;

    private static Dao<NoteSchema, Long> noteDao;

    public DatabaseManager(final String url) throws SQLException {
        Logger.setGlobalLogLevel(Level.OFF);
        conn = new JdbcPooledConnectionSource(url);
        noteDao = DaoManager.createDao(conn, NoteSchema.class);
        TableUtils.createTableIfNotExists(conn, NoteSchema.class);
    }

    @Override
    public String toString() {
        return "DatabaseManager []";
    }

    // CRUD

    public void createNote(final Note note) throws SQLException {
        noteDao.create(new NoteSchema(note));
    }

    public List<Note> getAllNotes() throws SQLException {
        final List<Note> notes = new ArrayList<>();
        for (final NoteSchema noteSchema : noteDao.queryForAll()) {
            notes.add(new Note(noteSchema));
        }
        return notes;
    }

    public Note readNote(final long id) throws SQLException {
        return new Note(noteDao.queryForId(id));
    }

    public void updateNote(final Note note) throws SQLException {
        noteDao.update(new NoteSchema(note));
    }

    public void deleteNote(final Note note) throws SQLException {
        noteDao.delete(new NoteSchema(note));
    }

}
