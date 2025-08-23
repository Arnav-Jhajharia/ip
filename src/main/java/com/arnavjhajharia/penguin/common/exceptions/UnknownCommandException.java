package com.arnavjhajharia.penguin.common.exceptions;

public class UnknownCommandException extends PenguinException {
    public UnknownCommandException(String cmd) {
        super("Unknown command: \"" + cmd + "\".");
    }
}
