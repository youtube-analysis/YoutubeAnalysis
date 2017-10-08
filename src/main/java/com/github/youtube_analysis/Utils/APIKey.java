package com.github.youtube_analysis.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Vitaly Kurotkin on 05.10.2017.
 */
public class APIKey {
    private String key;

    // Singleton
    public static final APIKey INSTANCE = new APIKey();

    public APIKey(){
        this.key = loadKey();
    }

    public String getKey(){
        return key;
    }

    // Загрузка ключа api из файла
    private static String loadKey(){
        try {
            FileReader fileReader = new FileReader("key.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            return reader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("Нет файла key.txt");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
