package org.todoer.database.models;

import java.text.MessageFormat;

import org.todoer.database.schemas.NoteSchema;

public class Note {
    private long id;
    private String title;
    private String content;

    public Note(final String title, final String content) {
        this.id = 1L + (long) (Math.random() * (10L - 1L));
        this.title = title;
        this.content = content;
    }

    public Note(final NoteSchema schema) {
        this.id = schema.getId();
        this.title = schema.getTitle();
        this.content = schema.getContent();
    }

    public Note() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Note'{'id={0}, title=''{1}'', content=''{2}'''}'", id, title, content);
    }

    public String format() {
        return MessageFormat.format("ID: {0}\nTitle:\n    {1}\nDesription:\n    {2}", id, title, content);
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}
