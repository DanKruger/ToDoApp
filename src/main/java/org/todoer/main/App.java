package org.todoer.main;

import org.todoer.client.ClientInterface;

/**
 * Hello world!
 */
public class App {
    private static Backend server;

    public static Backend getServer() {
        return server;
    }

    public static void setServer(final Backend server) {
        App.server = server;
    }

    public static void main(final String[] args) {
        try {
            server = new Backend("jdbc:sqlite:./todoer.sqlite");
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
        final ClientInterface clientInterface = new ClientInterface();
        clientInterface.start();
    }

    @Override
    public String toString() {
        return "App []";
    }
}
