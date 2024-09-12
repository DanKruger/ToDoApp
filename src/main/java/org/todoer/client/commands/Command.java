package org.todoer.client.commands;

public abstract class Command {
    private final String command;
    private final String description;

    public Command(final String command, final String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean execute();

}
