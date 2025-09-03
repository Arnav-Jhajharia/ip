package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.MissingArgumentException;
import com.arnavjhajharia.penguin.model.TaskList;
import com.arnavjhajharia.penguin.model.TaskType;

/**
 * Represents a command that adds a new task to the {@link TaskList}.
 * <p>
 * The command validates the raw user input string against the expected format
 * for the specified {@link TaskType} (TODO, DEADLINE, or EVENT) before
 * attempting to add it to the task list.
 */
public class AddCommand implements Command {

    /** The raw user input for the task description and metadata. */
    private final String raw;

    /** The type of task to be added (e.g., TODO, DEADLINE, EVENT). */
    private final TaskType type;

    /**
     * Constructs a new {@code AddCommand}.
     *
     * @param raw  the raw input string containing the task description and additional details
     * @param type the type of task to be created
     */
    public AddCommand(String raw, TaskType type) {
        this.raw = raw;
        this.type = type;
    }

    /**
     * Executes the add command by validating the input and adding a new task to the given task list.
     * <p>
     * - If the input is missing or invalid, throws a {@link MissingArgumentException}
     *   with details of the expected format.
     * - If valid, the task is added and a success message is returned.
     *
     * @param tasks the {@link TaskList} to which the new task will be added
     * @return a {@link CommandResult} containing the success message after adding the task
     * @throws MissingArgumentException if the input is missing or does not match the expected format
     */
    @Override
    public CommandResult execute(TaskList tasks) throws MissingArgumentException {
        if (raw == null || raw.isBlank()) {
            String expected = switch (type) {
                case TODO -> "todo <description>";
                case DEADLINE -> "deadline <desc> /<when>";
                case EVENT -> "event <desc> /<time> /<time>";
            };
            throw new MissingArgumentException(expected);
        }

        // Validation step for input format
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

        String msg = tasks.add(raw, type).toString();
        return CommandResult.of(msg);
    }
}
