package com.arnavjhajharia.penguin.model;

import com.arnavjhajharia.penguin.model.task.Task;

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

    public StringBuilder add(String prompt) {
        if (tasks.size() >= limit) {
            return new StringBuilder("Too grindy bro — task list is full (limit " + limit + ").");
        }

        StringBuilder returnText = new StringBuilder();

        Task task = new Task(prompt, tasks.size());

        tasks.add(task);
        return returnText
                .append("Damn busy bro! One more task has been added: \t")
                .append(task).append("\n")
                .append(String.format("Now you have %d tasks in your list", tasks.size()));
    }


    public int size() {
        return tasks.size();
    }



    // --- helpers ---
    public boolean isInvalidIndex(int id) {
        return id < 0 || id >= tasks.size();
    }
}
