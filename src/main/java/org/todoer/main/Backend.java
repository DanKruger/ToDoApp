package org.todoer.main;

import java.sql.SQLException;

import org.todoer.database.DatabaseManager;

public class Backend {
    private static DatabaseManager db;

    public static void setDb(final DatabaseManager db) {
        Backend.db = db;
    }

    public Backend(final String dbUrl) throws SQLException {
        setDb(new DatabaseManager(dbUrl));
    }

    @Override
    public String toString() {
        return "Backend []";
    }

    public DatabaseManager getDb() {
        return db;
    }

}
