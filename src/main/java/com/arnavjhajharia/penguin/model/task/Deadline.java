package com.arnavjhajharia.penguin.model.task;

public class Deadline extends Task {
    String deadline;
    public Deadline(String task, int id, String deadline) {
        super(task, id);
        this.deadline = deadline;
    }

    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadline);
    }

    @Override
    public String toStorageLine() {
        return String.format("T | %s | %s", doneFlag(), name);
    }
}
