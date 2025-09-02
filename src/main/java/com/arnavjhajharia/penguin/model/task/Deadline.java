package com.arnavjhajharia.penguin.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    private final String raw;
    private final LocalDate date;

    public Deadline(String task, int id, String deadline) {
        super(task, id);
        this.raw = deadline == null ? "" : deadline.trim();
        try {
            this.date = LocalDate.parse(this.raw, ISO);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid deadline format. Use yyyy-MM-dd (e.g., 2025-09-02). Got: " + this.raw
            );
        }
    }


    @Override
    public String toStorageLine() {
        return String.format("D | %s | %s | %s", doneFlag(), name, date.format(ISO));
    }

    @Override
    public String toString() {

        String pretty = date.format(DateTimeFormatter.ofPattern("MMM d, uuuu"));
        return String.format("[D] %s (by: %s)", super.toString(), pretty);
    }


    public LocalDate getDate() {
        return date;
    }
}
