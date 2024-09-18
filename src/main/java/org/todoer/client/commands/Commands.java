package org.todoer.client.commands;

import org.todoer.client.commands.subcommands.notes.CreateNote;
import org.todoer.client.commands.subcommands.notes.DeleteNote;
import org.todoer.client.commands.subcommands.HelpUser;
import org.todoer.client.commands.subcommands.notes.ListNotes;
import org.todoer.client.commands.subcommands.notes.ReadNote;
import org.todoer.client.commands.subcommands.notes.UpdateNote;
import org.todoer.client.commands.subcommands.todos.CreateTodo;
import org.todoer.client.commands.subcommands.todos.DeleteTodo;
import org.todoer.client.commands.subcommands.todos.ListTodos;
import org.todoer.client.commands.subcommands.todos.ReadTodo;
import org.todoer.client.commands.subcommands.todos.UpdateTodo;

public enum Commands {

    CREATE {
        String subcommand = "note";

        @Override
        public boolean isValidArgs(final String[] args) {
            boolean isValidLength = args.length <= 1;
            boolean isValidSubcommand = true;
            if (args.length != 0) {
                isValidSubcommand = (args[0].equalsIgnoreCase("note") || args[0].equalsIgnoreCase("todo"));
                subcommand = args[0];
            }
            return isValidLength && isValidSubcommand;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                return switch (subcommand) {
                    case ("note") -> new CreateNote();
                    case ("todo") -> new CreateTodo();
                    default -> new CreateNote();
                };
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    LIST {
        String subcommand = "note";

        @Override
        public boolean isValidArgs(final String[] args) {
            boolean isValidLength = args.length <= 1;
            boolean isValidSubcommand = true;
            if (args.length != 0) {
                isValidSubcommand = (args[0].equalsIgnoreCase("notes") || args[0].equalsIgnoreCase("todos"));
                subcommand = args[0];
            }
            return isValidLength && isValidSubcommand;
        }

        @Override
        public Command construct(final String[] args) {
            // TODO --Add todo functionality
            if (isValidArgs(args)) {
                return switch (subcommand) {
                    case ("notes") -> new ListNotes();
                    case ("todos") -> new ListTodos();
                    default -> new ListNotes();
                };
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    READ {

        String subcommand = "note";

        @Override
        public boolean isValidArgs(final String[] args) {
            boolean isValidLength = args.length == 2;
            boolean isValidSubcommand = true;
            if (isValidLength) {
                isValidSubcommand = (args[0].equalsIgnoreCase("note") || args[0].equalsIgnoreCase("todo"))
                        && (args[1].matches("[0-9]+"));
                subcommand = args[0];
            }
            return isValidLength && isValidSubcommand;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                return switch (subcommand) {
                    case ("note") -> new ReadNote(Long.valueOf(args[1]));
                    case ("todo") -> new ReadTodo(Long.valueOf(args[1]));
                    default -> new ReadNote(Long.valueOf(args[1]));
                };
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    UPDATE {
        String subcommand = "note";

        @Override
        public boolean isValidArgs(final String[] args) {
            boolean isValidLength = args.length == 2;
            boolean isValidSubcommand = true;
            if (isValidLength) {
                isValidSubcommand = (args[0].equalsIgnoreCase("note") || args[0].equalsIgnoreCase("todo"))
                        && (args[1].matches("[0-9]+"));
                subcommand = args[0];
            }
            return isValidLength && isValidSubcommand;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                return switch (subcommand) {
                    case ("note") -> new UpdateNote(Long.valueOf(args[1]));
                    case ("todo") -> new UpdateTodo(Long.valueOf(args[1]));
                    default -> new ReadNote(Long.valueOf(args[1]));
                };
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    DELETE {
        String subcommand = "note";

        @Override
        public boolean isValidArgs(final String[] args) {
            boolean isValidLength = args.length == 2;
            boolean isValidSubcommand = true;
            if (isValidLength) {
                isValidSubcommand = (args[0].equalsIgnoreCase("note") || args[0].equalsIgnoreCase("todo"))
                        && (args[1].matches("[0-9]+"));
                subcommand = args[0];
            }
            return isValidLength && isValidSubcommand;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                return switch (subcommand) {
                    case ("note") -> new DeleteNote(Long.valueOf(args[1]));
                    case ("todo") -> new DeleteTodo(Long.valueOf(args[1]));
                    default -> new DeleteNote(Long.valueOf(args[1]));
                };
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    HELP {
        @Override
        public boolean isValidArgs(final String[] args) {
            return args.length == 0;
        }

        @Override
        public Command construct(final String[] args) {
            return new HelpUser();
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
