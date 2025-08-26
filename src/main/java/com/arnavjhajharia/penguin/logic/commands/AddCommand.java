package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.MissingArgumentException;
import com.arnavjhajharia.penguin.model.TaskList;
import com.arnavjhajharia.penguin.model.TaskType;

public class AddCommand implements Command {
    private final String raw;
    private final TaskType type;

    public AddCommand(String raw, TaskType type) {
        this.raw = raw;
        this.type = type;
    }

    @Override
    public StringBuilder execute(TaskList tasks) throws MissingArgumentException {
        if (raw == null || raw.isBlank()) {
            String expected = switch (type) {
                case TODO -> "todo <description>";
                case DEADLINE -> "deadline <desc> /<when>";
                case EVENT -> "event <desc> /<time> /<time>";
            };
            throw new MissingArgumentException(expected);
        }

        // Validation step
        boolean valid = switch (type) {
            case TODO -> !raw.isBlank();
            case DEADLINE -> raw.contains("/") && raw.indexOf("/") != raw.length() - 1;
            case EVENT -> {
                int count = raw.length() - raw.replace("/", "").length();
                yield count >= 2; // must have at least 2 slashes
            }
        };

        if (!valid) {
            String expected = switch (type) {
                case TODO -> "todo <description>";
                case DEADLINE -> "deadline <desc> /<when>";
                case EVENT -> "event <desc> /<time> /<time>";
            };
            throw new MissingArgumentException("Invalid format. Expected: " + expected);
        }

        return tasks.add(raw, type); // Let TaskList parse fields as you already do
    }

}
