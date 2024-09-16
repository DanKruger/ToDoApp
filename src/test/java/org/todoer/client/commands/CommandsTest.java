package org.todoer.client.commands;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Command Tests")
class CommandsTest {

    @Nested
    @DisplayName("Create Command Tests")
    class CreateCommand {

        @Test
        @DisplayName("Create No args")
        public void validCreateNoArgs() {
            testIsValid("create");
        }

        @Test
        @DisplayName("Create Note")
        public void validCreateNote() {
            testIsValid("create note");
        }

        @Test
        @DisplayName("Create todo")
        public void validCreateTodo() {
            testIsValid("create todo");
        }

    }

    @Nested
    @DisplayName("List Command Tests")
    class ListCommand {

        @Test
        @DisplayName("List No Args")
        public void validListNoArg() {
            testIsValid("list");
        }

        @Test
        @DisplayName("List Notes")
        public void validListNotes() {
            testIsValid("list notes");
        }

        @Test
        @DisplayName("List Todos")
        public void validListTodo() {
            testIsValid("list todos");
        }
    }

    @Nested
    @DisplayName("Read Command Tests")
    class ReadCommand {

        @Test
        @DisplayName("Read No Arg")
        public void invalidReadNoArgs() {
            testInvalid("read");
        }

        @Test
        @DisplayName("Read beans")
        public void invalidReadNotEnoughArgs() {
            testInvalid("read beans");
        }

        @Test
        @DisplayName("Read Beans 10")
        public void invalidReadWrongSub() {
            testInvalid("read beans 10");
        }

        @Test
        @DisplayName("Read note beans")
        public void invalidReadWrongId() {
            testInvalid("read note beans");
        }

        @Test
        @DisplayName("Read Note 10")
        public void validReadNote() {
            testIsValid("read note 10");
        }

        @Test
        @DisplayName("Read Todo 10")
        public void validReadTodo() {
            testIsValid("read todo 10");
        }

    }

    @Nested
    @DisplayName("Update Command Tests")
    class UpdateCommand {

        @Test
        @DisplayName("Update No Args")
        public void invalidUpdateNoArgs() {
            testInvalid("update");
        }

        @Test
        @DisplayName("Update beans")
        public void invalidUpdateNotEnoughArgs() {
            testInvalid("update beans");
        }

        @Test
        @DisplayName("Update beans 10")
        public void invalidUpdateWrongSub() {
            testInvalid("update beans 10");
        }

        @Test
        @DisplayName("Update note beans")
        public void invalidUpdateWrongId() {
            testInvalid("update note beans");
        }

        @Test
        @DisplayName("Update Note 10")
        public void validUpdateNote() {
            testIsValid("update note 10");
        }

        @Test
        @DisplayName("Update Todo 10")
        public void validUpdateTodo() {
            testIsValid("update todo 10");
        }

    }

    @Nested
    @DisplayName("Delete Command Tests")
    class DeleteCommand {

        @Test
        @DisplayName("Delete No Args")
        public void invalidDeleteNoArgs() {
            testInvalid("delete");
        }

        @Test
        @DisplayName("Delete beans")
        public void invalidDeleteNotEnoughArgs() {
            testInvalid("delete beans");
        }

        @Test
        @DisplayName("Delete Beans 10")
        public void invalidDeleteWrongSub() {
            testInvalid("delete beans 10");
        }

        @Test
        @DisplayName("Delete note beans")
        public void invalidDeleteWrongId() {
            testInvalid("delete note beans");
        }

        @Test
        @DisplayName("Delete Note 10")
        public void validDeleteNote() {
            testIsValid("delete note 10");
        }

        @Test
        @DisplayName("Delete Todo 10")
        public void validDeleteTodo() {
            testIsValid("delete todo 10");
        }

    }

    private String[] getArgs(String command) {
        return Arrays.copyOfRange(command.split(" "), 1, command.split(" ").length);
    }

    private void testInvalid(String input) {
        String command = input.split(" ")[0].toUpperCase();
        String[] args = getArgs(input);
        Assertions.assertFalse(Commands.valueOf(command).isValidArgs(args));
    }

    private void testIsValid(String input) {
        String command = input.split(" ")[0].toUpperCase();
        String[] args = getArgs(input);
        Assertions.assertTrue(Commands.valueOf(command).isValidArgs(args));
    }

}
