package com.arnavjhajharia.penguin.logic.commands;
import com.arnavjhajharia.penguin.common.exceptions.PenguinException;

public interface Command {
    StringBuilder execute() throws PenguinException;
    default boolean isExit() { return false; }
}
