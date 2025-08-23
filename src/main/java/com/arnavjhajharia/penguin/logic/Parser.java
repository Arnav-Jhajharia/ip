package com.arnavjhajharia.penguin.logic;

import com.arnavjhajharia.penguin.common.exceptions.UnknownCommandException;
import com.arnavjhajharia.penguin.logic.commands.*;

public class Parser {

    public Command parse(String input) throws UnknownCommandException {
        if (input == null) throw new UnknownCommandException("(null)");
        String trimmed = input.trim();
        if (trimmed.isEmpty()) throw new UnknownCommandException("(empty)");


        return switch (trimmed) {
            case "bye"     -> new ByeCommand();
            default        -> new ParrotCommand(input);
        };
    }


}