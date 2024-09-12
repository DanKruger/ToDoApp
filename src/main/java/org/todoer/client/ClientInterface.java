package org.todoer.client;

import java.util.Arrays;
import java.util.Scanner;

import org.todoer.client.commands.Command;
import org.todoer.client.commands.Commands;

public class ClientInterface {
    private static Scanner scanner;

    public static String getInput(final String prompt) {
        System.out.print(prompt);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "quit";
    }

    public ClientInterface() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String toString() {
        return "ClientInterface []";
    }

    public void start() {
        inputLoop();
    }

    private void inputLoop() {
        System.out.println("Welcome");
        String input = " ";
        do {
            input = getInput("\033[32mcommand:\033[0m ");
            validateInput(input);
        } while (!input.equalsIgnoreCase("quit"));
    }

    private void validateInput(final String input) {
        final String[] tokens = input.split(" ");
        final String command = tokens[0];
        final String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
        boolean isValid = false;
        try {
            isValid = Commands.valueOf(command.toUpperCase()).isValidArgs(args);
        } catch (final IllegalArgumentException ignored) {
        }
        if (isValid) {
            final Command cmd = Commands.valueOf(command.toUpperCase()).construct(args);
            cmd.execute();
        } else {
            System.out.println("Invalid command");
        }
    }
}
