package com.arnavjhajharia.penguin.logic.commands;

public class CommandResult {
    private final String message;
    private final boolean isExit;

    public CommandResult(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    public String message() { return message; }
    public boolean isExit() { return isExit; }

    public static CommandResult of(String message) {
        return new CommandResult(message, false);
    }
    public static CommandResult exit(String message) {
        return new CommandResult(message, true);
    }
}
