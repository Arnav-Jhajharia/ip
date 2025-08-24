package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.model.TaskList;

public class ListCommand implements Command {
    @Override
    public StringBuilder execute(TaskList tasks) {
        return tasks.list();
    }
}
