package com.arnavjhajharia.penguin.app;


import com.arnavjhajharia.penguin.common.exceptions.PenguinException;
import com.arnavjhajharia.penguin.common.exceptions.UnknownCommandException;
import com.arnavjhajharia.penguin.logic.Parser;
import com.arnavjhajharia.penguin.logic.commands.Command;

import java.util.Scanner;


public class Simulator {
    private final Parser parser = new Parser();

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
            try {
                Command cmd = parser.parse(prompt);
                if (cmd.isExit()) { end(); return; }

                String body = String.valueOf(cmd.execute());
                System.out.println("--------------------------------");
                System.out.println(body);
                System.out.println("--------------------------------");
            }
            catch(PenguinException e) {
                System.out.println("--------------------------------");
                System.out.println("Error: " + e.getMessage());
                System.out.println("--------------------------------");
            }




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
