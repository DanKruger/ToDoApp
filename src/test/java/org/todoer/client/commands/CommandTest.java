package org.todoer.client.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Nested
    @DisplayName("Note Commands")
    class NoteCommands {
        private static final Command.TYPE NOTE = Command.TYPE.NOTES;

        @Test
        @DisplayName("Create Note")
        public void createNote() {
            testCommand("create Note", NOTE);
            testCommand("create", NOTE);
        }

        @Test
        @DisplayName("Read Note 1")
        public void readNote() {
            testCommand("read note 1", NOTE);
            testInvalid("read", NOTE);
        }

        @Test
        @DisplayName("Update Note 1")
        public void updateNote() {
            testCommand("update note 1", NOTE);
            testInvalid("update", NOTE);
        }

        @Test
        @DisplayName("Delete Note 1")
        public void deleteNote() {
            testCommand("delete note 1", NOTE);
            testInvalid("delete", NOTE);
        }

        @Test
        @DisplayName("List Notes")
        public void listNotes() {
            testCommand("list notes", NOTE);
            testCommand("list", NOTE);
        }
    }

    @Nested
    @DisplayName("Todo Commands")
    class TodoCommands {
        private static final Command.TYPE TODO = Command.TYPE.TODOS;

        @Test
        @DisplayName("Create Todo")
        public void createTodo() {
            testCommand("create todo", TODO);
            testCommand("create", TODO);
        }

        @Test
        @DisplayName("Read Todo 1")
        public void readTodo() {
            testCommand("read todo 1", TODO);
            testInvalid("read", TODO);
        }

        @Test
        @DisplayName("Update Todo 1")
        public void updateTodo() {
            testCommand("update todo 1", TODO);
            testInvalid("update", TODO);
        }

        @Test
        @DisplayName("Delete Todo 1")
        public void deleteTodo() {
            testCommand("delete todo 1", TODO);
            testInvalid("delete", TODO);
        }

        @Test
        @DisplayName("List Todos")
        public void listTodos() {
            testCommand("list todos", TODO);
            testCommand("list", TODO);
        }

    }

    private void testInvalid(final String input, final Command.TYPE type) {
        assertThrows(IllegalArgumentException.class, () -> testCommand(input, type));
    }

    private void testCommand(final String input, final Command.TYPE type) {
        final Command command = getCommand(input);
        assertNotNull(command);
        assertEquals(type, command.getType());
        assertEquals(command.getCommand(), input.split(" ")[0]);
        assertFalse(command.getDescription().isEmpty());
    }

    private Command getCommand(final String input) {
        final String command = input.split(" ")[0].toUpperCase();
        final String[] args = getArgs(input);
        return Commands.valueOf(command).construct(args);
    }

    private String[] getArgs(final String command) {
        return Arrays.copyOfRange(command.split(" "), 1, command.split(" ").length);
    }
}
