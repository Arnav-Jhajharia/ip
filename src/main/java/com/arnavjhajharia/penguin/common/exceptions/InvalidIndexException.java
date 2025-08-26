package com.arnavjhajharia.penguin.common.exceptions;

public class InvalidIndexException extends PenguinException {
    public InvalidIndexException(String raw) { super("Invalid task index: \"" + raw + "\"."); }
    public InvalidIndexException(int idx)   { super("Invalid task index: " + (idx + 1) + "."); }
}
