package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.model.TaskList;

public class ByeCommand implements Command {
    @Override
    public StringBuilder execute(TaskList tasks) {
        // Actual exit message handled by Simulator (so tests can control exit)
        return new StringBuilder("bye");
    }
    @Override public boolean isExit() { return true; }
}
