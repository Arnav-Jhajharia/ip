package com.arnavjhajharia.penguin.app;

import com.arnavjhajharia.penguin.ui.ConsoleUi;
import com.arnavjhajharia.penguin.ui.Ui;

import java.util.Scanner;

/**
 * Entry point of the Penguin application.
 * <p>
 * Initializes the user interface and the {@link Simulator}, then starts
 * the main program loop.
 */
public class Main {

    /**
     * The main method that launches the Penguin application.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ui ui = new ConsoleUi(sc);
        Simulator s = new Simulator("data/duke.txt", ui);

        s.start();
    }
}
