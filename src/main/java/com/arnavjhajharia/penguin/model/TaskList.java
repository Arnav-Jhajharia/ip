package com.arnavjhajharia.penguin.model;

import com.arnavjhajharia.penguin.logic.FileParser;
import com.arnavjhajharia.penguin.model.task.Deadline;
import com.arnavjhajharia.penguin.model.task.Event;
import com.arnavjhajharia.penguin.model.task.Task;
import com.arnavjhajharia.penguin.model.task.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskList {

    private final List<Task> tasks;
    private final int limit;
    private Optional<String> fileName;

    public TaskList(int limit) {
        this.tasks = new ArrayList<>();
        this.limit = limit;
        this.fileName = Optional.empty();
    }

    public TaskList(int limit, String fileName) {
        this.limit = limit;
        this.fileName = Optional.ofNullable(fileName);
        this.tasks = new ArrayList<>();
        loadFromFileIfPresent();   // <<— load on construction
    }

    public void loadFromFile(String filePath) {
        this.fileName = Optional.ofNullable(filePath);
        loadFromFileIfPresent();
    }

    private void loadFromFileIfPresent() {
        if (fileName.isEmpty()) return;

        List<String> lines = FileParser.readLinesFromFile(fileName.get());
        for (String line : lines) {
            if (tasks.size() >= limit) break;
            Task parsed = parseLineToTask(line, tasks.size());
            if (parsed != null) {
                tasks.add(parsed);
            }
        }
    }

    public boolean save() {
        return fileName.filter(this::saveToFile).isPresent();
    }

    public boolean saveToFile(String filePath) {
        List<String> lines = tasks.stream()
                .map(Task::toStorageLine)   // <<— polymorphic call
                .collect(Collectors.toList());
        return FileParser.writeLinesToFile(filePath, lines);
    }



    /**
     * Supported formats:
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | Aug 6th 2-4pm
     * E | 1 | standup | Aug 7th 10am to 11am
     */
    private Task parseLineToTask(String line, int nextId) {
        if (line == null) return null;
        String trimmed = line.trim();
        if (trimmed.isEmpty()) return null;

        // Split on pipes with optional surrounding whitespace
        String[] parts = trimmed.split("\\s*\\|\\s*");
        // Expect at least: type | done | desc
        if (parts.length < 3) return null;

        String type = parts[0].trim().toUpperCase();
        String doneFlag = parts[1].trim();
        String desc = parts[2].trim();

        Task t = switch (type) {
            case "T" -> new Todo(desc, nextId);
            case "D" -> {
                String deadline = parts.length >= 4 ? parts[3].trim() : "";
                yield new Deadline(desc, nextId, deadline);
            }
            case "E" -> {

                if (parts.length < 5) {
                    throw new IllegalArgumentException(
                            "Invalid Event line. Expected: E | <0/1> | <desc> | yyyy-MM-dd'T'HH:mm | yyyy-MM-dd'T'HH:mm"
                    );
                }
                String start = parts[3].trim();
                String end   = parts[4].trim();
                yield new Event(desc, nextId, start, end);
            }
            default -> null;
        };

        if (t != null && ("1".equals(doneFlag) || "true".equalsIgnoreCase(doneFlag))) {
            t.markDone();
        }
        return t;
    }

    public StringBuilder list() {
        if (tasks.isEmpty()) {
            return new StringBuilder("No items to list bro");
        }
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            text.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return text;
    }

    public StringBuilder add(String prompt, TaskType type) {
        if (tasks.size() >= limit) {
            return new StringBuilder("Too grindy bro — task list is full (limit " + limit + ").");
        }

        StringBuilder returnText = new StringBuilder();
        String[] parts = prompt.split(" /");

        Task task = null;
        switch (type) {
            case TODO -> {
                String desc = parts[0];
                task = new Todo(desc, tasks.size());
            }
            case DEADLINE -> {
                String desc = parts[0];
                String by = parts.length > 1 ? parts[1] : "";
                task = new Deadline(desc, tasks.size(), by);
            }
            case EVENT -> {
                String desc = parts[0];
                String from = parts.length > 1 ? parts[1] : "";
                String to = parts.length > 2 ? parts[2] : "";
                task = new Event(desc, tasks.size(), from, to);
            }
        }

        tasks.add(task);
        return returnText
                .append("Damn busy bro! One more task has been added\t")
                .append(task).append("\n")
                .append(String.format("Now you have %d tasks in your list", tasks.size()));
    }

    public StringBuilder markDone(int id) {
        if (isInvalidIndex(id)) {
            return new StringBuilder("Bro that task number doesn’t exist.");
        }
        Task t = tasks.get(id);
        t.markDone();
        return new StringBuilder("Damn you not chill, completing tasks and stuff! I've marked this task as done:\n")
                .append(t);
    }

    public StringBuilder markUndone(int id) {
        if (isInvalidIndex(id)) {
            return new StringBuilder("Bro that task number doesn’t exist.");
        }
        Task t = tasks.get(id);
        t.markUndone();
        return new StringBuilder("Ah you were just lying to yourself. It's chill! I've marked this task as undone:\n")
                .append(t);
    }

    public StringBuilder delete(int idx) {
        if (isInvalidIndex(idx)) {
            return new StringBuilder("Bro that task number doesn’t exist.");
        }
        Task removed = tasks.remove(idx);
        StringBuilder sb = new StringBuilder();
        sb.append(" Noted. I've removed this task:\n")
                .append("   ").append(removed).append("\n")
                .append(" Now you have ")
                .append(size())
                .append(size() == 1 ? " task" : " tasks")
                .append(" in the list.");
        return sb;
    }

    public int size() {
        return tasks.size();
    }

    // --- helpers ---
    public boolean isInvalidIndex(int id) {
        return id < 0 || id >= tasks.size();
    }


}
