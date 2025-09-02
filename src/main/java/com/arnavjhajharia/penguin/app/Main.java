package com.arnavjhajharia.penguin.app;

import com.arnavjhajharia.penguin.ui.ConsoleUi;
import com.arnavjhajharia.penguin.ui.Ui;

import java.util.*;


public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ui ui = new ConsoleUi(sc);
        Simulator s = new Simulator("data/duke.txt", ui);

        s.start();
    }


}
