package com.arnavjhajharia.penguin.app;

import com.arnavjhajharia.penguin.common.exceptions.UnknownCommandException;

import java.util.*;


public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Simulator s = new Simulator("data/duke.txt");

        s.start(sc);
    }


}
