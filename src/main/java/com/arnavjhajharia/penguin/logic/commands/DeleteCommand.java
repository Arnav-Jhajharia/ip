package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.InvalidIndexException;
import com.arnavjhajharia.penguin.model.TaskList;

public class DeleteCommand implements Command {
    private final String rawIndex;

    public DeleteCommand(String rawIndex) {
        this.rawIndex = rawIndex;
    }

    @Override
    public StringBuilder execute(TaskList tasks) throws InvalidIndexException {
        int idx;
        try {
            idx = Integer.parseInt(rawIndex) - 1; // user is 1-based
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(rawIndex);
        }
        if (tasks.isInvalidIndex(idx)) throw new InvalidIndexException(idx);

        // Let TaskList do the actual removal + message formatting
        return tasks.delete(idx);
    }
}
