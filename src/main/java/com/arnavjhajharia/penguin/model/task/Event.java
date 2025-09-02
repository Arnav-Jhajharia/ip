package com.arnavjhajharia.penguin.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // yyyy-MM-dd'T'HH:mm[:ss[.SSS]]
    private static final DateTimeFormatter PRETTY = DateTimeFormatter.ofPattern("MMM d, uuuu h:mm a");

    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String task, int id, String startStr, String endStr) {
        super(task, id);
        if (startStr == null || endStr == null) {
            throw new IllegalArgumentException(
                    "Event requires start and end in yyyy-MM-dd'T'HH:mm (e.g., 2025-09-02T14:30)."
            );
        }
        try {
            this.start = LocalDateTime.parse(startStr.trim(), ISO);
            this.end   = LocalDateTime.parse(endStr.trim(), ISO);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid event datetime format. Use yyyy-MM-dd'T'HH:mm (e.g., 2025-09-02T14:30). Got: "
                            + startStr + " / " + endStr
            );
        }
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException("Event end must be after start.");
        }
    }

    @Override
    public String toStorageLine() {
        return String.format("E | %s | %s | %s | %s",
                doneFlag(), name, start.format(ISO), end.format(ISO));
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)",
                super.toString(),
                start.format(PRETTY),
                end.format(PRETTY));
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd()   { return end; }
}
