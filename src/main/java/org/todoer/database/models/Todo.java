
package org.todoer.database.models;

import java.text.MessageFormat;

import org.todoer.database.schemas.TodoSchema;

public class Todo {
    private long id;
    private String title;
    private String content;
    private boolean complete = false;

    public Todo(final String title, final String content) {
        this.id = 1L + (long) (Math.random() * (10L - 1L));
        this.title = title;
        this.content = content;
    }

    public Todo(final TodoSchema schema) {
        this.id = schema.getId();
        this.title = schema.getTitle();
        this.content = schema.getContent();
        this.complete = schema.isComplete();
    }

    public Todo() {
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(final boolean complete) {
        this.complete = complete;
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
        return MessageFormat.format("Todo'{'id={0}, title=''{1}'', content=''{2}'''}'", id, title, content);
    }

    public String format() {
        String cTitle = complete ? "\033[9m" + title + "\033[0m" : title;
        return MessageFormat.format("{0}. [{1}] {2}", id, complete ? "X" : " ", cTitle);
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
