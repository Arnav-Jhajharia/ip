package com.arnavjhajharia.penguin.model.task;

public class Todo extends Task {

    public Todo(String task, int id) {
        super(task, id);
    }

    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public String toStorageLine() {
        return String.format("T | %s | %s", doneFlag(), name);
    }
}

