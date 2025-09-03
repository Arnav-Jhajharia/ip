package com.arnavjhajharia.penguin.logic;

import com.arnavjhajharia.penguin.common.exceptions.MissingArgumentException;
import com.arnavjhajharia.penguin.common.exceptions.UnknownCommandException;
import com.arnavjhajharia.penguin.logic.commands.*;
import com.arnavjhajharia.penguin.model.TaskType;

public class Parser {

    public Command parse(String input) throws UnknownCommandException, MissingArgumentException {
        if (input == null) throw new UnknownCommandException("(null)");
        String trimmed = input.trim();
        if (trimmed.isEmpty()) throw new UnknownCommandException("(empty)");

        String[] parts = trimmed.split("\\s+", 2);
        String cmd = parts[0];
        String arg = (parts.length >= 2) ? parts[1] : "";

        return switch (cmd) {
            case "list"    -> new ListCommand();
            case "todo"    -> new AddCommand(arg, TaskType.TODO);
            case "deadline"-> new AddCommand(arg, TaskType.DEADLINE);
            case "event"   -> new AddCommand(arg, TaskType.EVENT);
            case "mark"    -> requireArgThen(new MarkCommand(arg, true), arg, "mark <index>");
            case "unmark"  -> requireArgThen(new MarkCommand(arg, false), arg, "unmark <index>");
            case "delete"  -> requireArgThen(new DeleteCommand(arg), arg, "delete <index>");
            case "find"    -> requireArgThen(new FindCommand(arg), arg, "find <substring>");
            case "bye"     -> new ByeCommand();
            default        -> throw new UnknownCommandException(cmd);
        };
    }

    private Command requireArgThen(Command c, String arg, String expected) throws MissingArgumentException {
        if (arg == null || arg.isBlank()) throw new MissingArgumentException(expected);
        return c;
    }
}