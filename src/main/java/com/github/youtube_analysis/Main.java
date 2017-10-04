package com.github.youtube_analysis;

import com.github.youtube_analysis.Controllers.MainDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private static String key;
    private Stage primaryStage;
    private BorderPane mainLayout;
    private Scene scene;
    private AnchorPane rootLayout;

    public static String getName(){
        return "Youtube Analysis ver.0.0.1";
    }

    public static String getAbout(){
        return "Приложение для анализа YouTube каналов, сбора статистика " +
                "и сравнения каналов по различным пользовательским запросам.";
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Search in Youtube");
        initLayout();
        //loadKey();

        initMainLayout();
        showMainDialog();
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

    public void initMainLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainLayout.fxml"));
        mainLayout = loader.load();
        scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMainDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainDialog.fxml"));
        AnchorPane mainDialog = loader.load();
        mainLayout.setCenter(mainDialog);

        MainDialogController controller = loader.getController();
        controller.setMainClass(this);
        controller.setAboutField("Youtube Analysis", "Эта программа ...");
    }

    private void initLayout() {
        URL url = getClass().getResource("/MainDialog.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            rootLayout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Url is " + url);
        }


        //nameField.setText(Main.getName());
        //nameField.setText(Main.getAbout());
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }
}
