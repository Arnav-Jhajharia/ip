package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.model.TaskList;

public class ListCommand implements Command {
    @Override
    public CommandResult execute(TaskList tasks) {

        String msg = tasks.list().toString();
        return CommandResult.of(msg);

    }
}
