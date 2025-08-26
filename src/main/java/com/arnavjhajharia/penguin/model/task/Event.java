package com.arnavjhajharia.penguin.model.task;

public class Event extends Task {
    String start;
    String end;

    public Event(String task, int id, String start, String end) {
        super(task, id);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), start, end);
    }
}
