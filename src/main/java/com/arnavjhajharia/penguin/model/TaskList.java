package com.arnavjhajharia.penguin.model;

import com.arnavjhajharia.penguin.model.task.Deadline;
import com.arnavjhajharia.penguin.model.task.Event;
import com.arnavjhajharia.penguin.model.task.Task;
import com.arnavjhajharia.penguin.model.task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;
    private final int limit;

    public TaskList(int limit) {
        this.tasks = new ArrayList<>();
        this.limit = limit;
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
        String[] parts = prompt.split(" /"); // keeps your existing input style

        Task task = null;
        switch (type) {
            case TODO -> {
                // todo <desc>
                String desc = parts[0];
                task = new Todo(desc, tasks.size());
            }
            case DEADLINE -> {
                // deadline <desc> /by <...>
                String desc = parts[0];
                String by = parts.length > 1 ? parts[1] : "";
                task = new Deadline(desc, tasks.size(), by);
            }
            case EVENT -> {
                // event <desc> /from <...> /to <...>
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

    public int size() {
        return tasks.size();
    }

    // --- helpers ---
    public boolean isInvalidIndex(int id) {
        return id < 0 || id >= tasks.size();
    }
}
