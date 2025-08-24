package com.arnavjhajharia.penguin.logic.commands;
import com.arnavjhajharia.penguin.common.exceptions.PenguinException;
import com.arnavjhajharia.penguin.model.TaskList;

public interface Command {
    StringBuilder execute(TaskList tasks) throws PenguinException;
    default boolean isExit() { return false; }
}
