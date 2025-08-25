package com.arnavjhajharia.penguin.logic;

import com.arnavjhajharia.penguin.common.exceptions.UnknownCommandException;
import com.arnavjhajharia.penguin.logic.commands.*;

public class Parser {

    public Command parse(String input) throws UnknownCommandException {
        if (input == null) throw new UnknownCommandException("(null)");
        String trimmed = input.trim();
        if (trimmed.isEmpty()) throw new UnknownCommandException("(empty)");

        String[] parts = trimmed.split("\\s+", 2);
        String cmd = parts[0];
        String arg = (parts.length >= 2) ? parts[1] : "";

        return switch (cmd) {
            case "list"    -> new ListCommand();
            case "add"    -> new AddCommand(arg);
            case "bye"     -> new ByeCommand();
            default        -> throw new UnknownCommandException("Not a known command");
        };
    }


}