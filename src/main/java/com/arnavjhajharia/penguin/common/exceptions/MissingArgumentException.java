package com.arnavjhajharia.penguin.common.exceptions;

public class MissingArgumentException extends PenguinException {
    public MissingArgumentException(String expected) {
        super("Missing/invalid arguments. Expected: " + expected);
    }
}
