package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.MissingArgumentException;
import com.arnavjhajharia.penguin.model.TaskList;

public class AddCommand implements Command {
    private final String raw;

    public AddCommand(String raw) {
        this.raw = raw;
    }

    @Override
    public StringBuilder execute(TaskList tasks) throws MissingArgumentException {
        if (raw == null || raw.isBlank()) {
            String expected = "task <task_name>";
            throw new MissingArgumentException(expected);
        }
        return tasks.add(raw);
    }
}
