package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.MissingArgumentException;
import com.arnavjhajharia.penguin.model.TaskList;
import com.arnavjhajharia.penguin.model.TaskType;

public class AddCommand implements Command {
    private final String raw;
    private final TaskType type;

    public AddCommand(String raw, TaskType type) {
        this.raw = raw;
        this.type = type;
    }

    @Override
    public StringBuilder execute(TaskList tasks) throws MissingArgumentException {
        if (raw == null || raw.isBlank()) {
            String expected = switch (type) {
                case TODO -> "todo <description>";
                case DEADLINE -> "deadline <desc> /by <yyyy-mm-dd>";
                case EVENT -> "event <desc> /from <time> /to <time>";
            };
            throw new MissingArgumentException(expected);
        }
        return tasks.add(raw, type); // Let TaskList parse fields as you already do
    }
}
