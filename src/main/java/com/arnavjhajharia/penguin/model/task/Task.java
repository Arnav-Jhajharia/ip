package com.arnavjhajharia.penguin.model.task;

public class Task {
    int id;
    String name;

    public Task(String task, int id) {
        name = task;
        this.id = id;
    }


    public String toString() {

        return String.format("%s",
                                name
                            );

    }


}
