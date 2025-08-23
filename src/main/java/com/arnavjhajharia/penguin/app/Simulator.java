package com.arnavjhajharia.penguin.app;


import java.util.Scanner;


public class Simulator {

    public void introduction() {
        String penguinStart =
                "          .--.                    \n" +
                        "         |o_o |   PENGUIN v0.1\n" +
                        "         |:_/ |   chill. simple. smooth.\n" +
                        "        //   \\\\ \\\\   \n" +
                        "       (|     | )   🥤  [Oreo Shake Inside]\n" +
                        "      /'\\\\_   _/`\\\\\n" +
                        "      \\\\___)=(___/\n";
        System.out.println(penguinStart);
        System.out.println("-------------------------------");
        System.out.println("Let's start!");
        System.out.println("-------------------------------");
    }



    public void start(Scanner sc) {

        introduction();

        while (true) {
            String prompt = sc.nextLine();

            if (prompt.equals("bye")) { end(); return; }

            System.out.println("--------------------------------");
            System.out.println(prompt);
            System.out.println("--------------------------------");

        }
    }

    private void end() {
        String penguinExit = """
                ======================================
                        Thanks for using PENGUIN
                          Have a chill day 🐧🥤
                ======================================
                
                          .--.                    
                         |o_o |   
                         |:_/ |   goodbye!
                        //   \\ \\   
                       (|     | )  
                      /'\\_   _/`\\
                      \\___)=(___/
                """;

        System.out.println(penguinExit);
        System.exit(0);
    }




}
