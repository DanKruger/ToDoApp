package org.todoer.client.commands;

import org.todoer.client.commands.subcommands.CreateNote;
import org.todoer.client.commands.subcommands.DeleteNote;
import org.todoer.client.commands.subcommands.HelpUser;
import org.todoer.client.commands.subcommands.ListNotes;
import org.todoer.client.commands.subcommands.ReadNote;
import org.todoer.client.commands.subcommands.UpdateNote;

public enum Commands {
    /**
     * The Forward command.
     * It should have only one argument and it should be a positive number.
     */
    CREATE {
        @Override
        public boolean isValidArgs(final String[] args) {
            return args.length <= 1;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                return new CreateNote();
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    /**
     * The Back command.
     * It should have only one argument and it should be a positive number.
     */
    LIST {
        @Override
        public boolean isValidArgs(final String[] args) {
            return args.length == 0;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                return new ListNotes();
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    /**
     * The Turn command.
     * It should only have one argument
     * and it should be either "Left" or "Right".
     */
    READ {
        @Override
        public boolean isValidArgs(final String[] args) {
            return args.length == 1;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                return new ReadNote(Long.valueOf(args[0]));
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    /**
     * The State command.
     * It should not have any arguments.
     */
    UPDATE {
        @Override
        public boolean isValidArgs(final String[] args) {
            return args.length == 1;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                return new UpdateNote(Long.valueOf(args[0]));
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
    },

    /**
     * The Look command.
     * It should not have any arguments.
     */
    DELETE {

        @Override
        public boolean isValidArgs(final String[] args) {
            return args.length == 1;
        }

        @Override
        public Command construct(final String[] args) {
            if (isValidArgs(args)) {
                return new DeleteNote(Long.valueOf(args[0]));
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
