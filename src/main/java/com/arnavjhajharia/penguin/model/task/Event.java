package com.arnavjhajharia.penguin.model.task;

public class Event extends Task {
    String start;
    String end;

    public Event(String task, int id, String start, String end) {
        super(task, id);
        this.start = start;
        this.end = end;
    }

    public String toStorageLine() {
        // Save as a single fourth field. If end is blank, keep only start.
        String when = (end == null || end.isBlank()) ? start : (start + " to " + end);
        return String.format("E | %s | %s | %s", doneFlag(), name, when);
    }

    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), start, end);
    }
}
