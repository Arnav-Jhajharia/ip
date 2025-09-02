package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.model.TaskList;

public class ByeCommand implements Command {
    @Override
    public CommandResult execute(TaskList tasks) {
        // Actual exit message handled by Simulator (so tests can control exit)
        return CommandResult.exit("bye");
    }
    @Override public boolean isExit() { return true; }
}
