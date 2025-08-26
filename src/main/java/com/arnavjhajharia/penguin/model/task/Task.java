package com.arnavjhajharia.penguin.model.task;

public abstract class Task {
    int id;
    String name;
    boolean isDone;

    public Task(String task, int id) {
        name = task;
        this.id = id;
    }

    public void markUndone() {
        isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public String toString() {

        return String.format("[%s] %s",
                isDone ? "X" : "",
                name
        );

    }


}
