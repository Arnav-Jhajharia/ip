package com.arnavjhajharia.penguin.logic.commands;

public class ParrotCommand implements Command {

    String prompt;

    public ParrotCommand(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public StringBuilder execute() {
        return new StringBuilder(prompt);
    }


}
