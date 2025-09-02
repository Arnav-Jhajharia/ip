package com.arnavjhajharia.penguin.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class FileParser {
    public static List<String> readLinesFromFile(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // return empty list if something fails
        }
    }

    public static boolean writeLinesToFile(String filePath, List<String> lines) {
        try {
            Path p = Path.of(filePath);
            if (p.getParent() != null) {
                Files.createDirectories(p.getParent());
            }
            Files.write(p, lines);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // signal failure to caller
        }
    }

}
