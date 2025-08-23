package com.arnavjhajharia.penguin.logic.commands;

public class ByeCommand implements Command {
    @Override
    public StringBuilder execute() {
        // Actual exit message handled by Simulator (so tests can control exit)
        return new StringBuilder("bye");
    }
    @Override public boolean isExit() { return true; }
}
