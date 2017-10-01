package com.github.youtube_analysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static String key;

    public static void main(String[] args) {
        loadKey();

    }

    private static void loadKey(){
        try {
            FileReader fileReader = new FileReader("key.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            key = reader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("Нет файла key.txt");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
