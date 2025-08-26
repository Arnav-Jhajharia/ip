package com.arnavjhajharia.penguin.common.exceptions;

public class UnknownCommandException extends PenguinException {
    public UnknownCommandException(String cmd) {
        super("It's chill but what do you mean lol: \"" + cmd + "\".");
    }
}
