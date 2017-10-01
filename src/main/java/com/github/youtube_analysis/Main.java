package com.github.youtube_analysis;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {
    private static String key;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadKey();
    }

    // Загрузка ключа API из файла
    private static void loadKey(){
        try {
            FileReader fileReader = new FileReader("key.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            key = reader.readLine();
        } catch (FileNotFoundException e) {
            String errorMessage = "Нет файла key.txt";
            System.out.println(errorMessage);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
