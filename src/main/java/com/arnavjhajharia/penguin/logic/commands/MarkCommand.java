package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.InvalidIndexException;
import com.arnavjhajharia.penguin.model.TaskList;

public class MarkCommand implements Command {
    private final String rawIndex;
    private final boolean markDone;

    public MarkCommand(String rawIndex, boolean markDone) {
        this.rawIndex = rawIndex;
        this.markDone = markDone;
    }

    @Override
    public StringBuilder execute(TaskList tasks) throws InvalidIndexException {
        int idx;
        try { idx = Integer.parseInt(rawIndex) - 1; }
        catch (NumberFormatException e) { throw new InvalidIndexException(rawIndex); }

        if (tasks.isInvalidIndex(idx)) throw new InvalidIndexException(idx);

        return markDone ? tasks.markDone(idx) : tasks.markUndone(idx);
    }
}
