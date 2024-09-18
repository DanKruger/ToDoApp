package org.todoer.client.commands;

import org.todoer.client.commands.subcommands.HelpUser;
import org.todoer.client.commands.subcommands.notes.*;
import org.todoer.client.commands.subcommands.todos.*;

/**
 * Contains command definitions and constraints
 */
public enum Commands {

    /**
     * Create command
     * supports todos and notes
     */
    CREATE {
        String subcommand = "note";

        @Override
        public boolean isValidArgs(final String[] args) {
            final boolean isValidLength = args.length <= 1;
            boolean isValidSubcommand = true;
            if (args.length != 0) {
                String string = args[0];
                isValidSubcommand = (string.equalsIgnoreCase("note")
                                    || string.equalsIgnoreCase("todo"));
                subcommand = string;
            }
            return isValidLength && isValidSubcommand;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                if (subcommand.equals("todo")) return new CreateTodo();
                return new CreateNote();
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    /**
     * Delete command
     * supports todos and notes
     */
    DELETE {
        String subcommand = "note";

        @Override
        public boolean isValidArgs(final String[] args) {
            final boolean isValidLength = args.length == 2;
            boolean isValidSubcommand = true;
            if (isValidLength) {
                String string = args[0];
                isValidSubcommand = (string.equalsIgnoreCase("note") || string.equalsIgnoreCase("todo"))
                        && (args[1].matches("[0-9]+"));
                subcommand = string;
            }
            return isValidLength && isValidSubcommand;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                if(subcommand.equals("todo")) return new DeleteTodo(Long.parseLong(args[1]));
                return new DeleteNote(Long.parseLong(args[1]));
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    /**
     * The help command
     * prints out usage information to the terminal
     */
    HELP {
        @Override
        public boolean isValidArgs(final String[] args) {
            return args.length == 0;
        }

        @Override
        public Command construct(final String[] args) {
            return new HelpUser();
        }

    },

    /**
     * List command
     * supports todos and notes
     */
    LIST {
        String subcommand = "note";

        @Override
        public boolean isValidArgs(final String[] args) {
            final boolean isValidLength = args.length <= 1;
            boolean isValidSubcommand = true;
            if (args.length != 0) {
                String string = args[0];
                isValidSubcommand = (string.equalsIgnoreCase("notes") || string.equalsIgnoreCase("todos"));
                subcommand = string;
            }
            return isValidLength && isValidSubcommand;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                if (subcommand.equals("todos")) return new ListTodos();
                return new ListNotes();
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    /**
     * Read command
     * Supports todos and notes
     */
    READ {

        String subcommand = "note";

        @Override
        public boolean isValidArgs(final String[] args) {
            final boolean isValidLength = args.length == 2;
            boolean isValidSubcommand = true;
            if (isValidLength) {
                String string = args[0];
                isValidSubcommand = (string.equalsIgnoreCase("note") || string.equalsIgnoreCase("todo"))
                        && (args[1].matches("[0-9]+"));
                subcommand = string;
            }
            return isValidLength && isValidSubcommand;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                if (subcommand.equalsIgnoreCase("todo")) return new ReadTodo(Long.parseLong(args[1]));
                return new ReadNote(Long.parseLong(args[1]));
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    /**
     * Update command
     * supports todos and notes
     */
    UPDATE {
        String subcommand = "note";

        @Override
        public boolean isValidArgs(final String[] args) {
            final boolean isValidLength = args.length == 2;
            boolean isValidSubcommand = true;
            if (isValidLength) {
                String string = args[0];
                isValidSubcommand = (string.equalsIgnoreCase("note") || string.equalsIgnoreCase("todo"))
                        && (args[1].matches("[0-9]+"));
                subcommand = string;
            }
            return isValidLength && isValidSubcommand;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                if(subcommand.equalsIgnoreCase("todo")) return new UpdateTodo(Long.parseLong(args[1]));
                return new UpdateNote(Long.parseLong(args[1]));
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    };

    /**
     * Check that the arguments are valid for respective command.
     *
     * @param args
     * @return True or False
     */
    public abstract boolean isValidArgs(String[] args);

    public abstract Command construct(String[] args);

}
