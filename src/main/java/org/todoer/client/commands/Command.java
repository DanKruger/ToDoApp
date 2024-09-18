package org.todoer.client.commands;

public abstract class Command {
    private final String command;
    private final String description;

    public enum TYPE {NOTES, TODOS}

    private TYPE type;

    public Command(final String command, final String description, final TYPE type) {
        this.command = command;
        this.description = description;
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean execute();

}
