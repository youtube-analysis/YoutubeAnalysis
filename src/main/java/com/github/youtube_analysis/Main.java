package com.github.youtube_analysis;

import com.github.youtube_analysis.Controllers.MainDialogController;
import com.github.youtube_analysis.Controllers.SettingsDialogController;
import com.github.youtube_analysis.Controllers.TaskDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
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
        this.primaryStage.getIcons().add(new Image("/images/icon.png"));

        initMainLayout();
        showMainDialog();
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
        controller.setAboutField(getName(), getAbout());
    }

    public void showTaskDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/TaskDialog.fxml"));
        AnchorPane taskDialog = loader.load();
        mainLayout.setCenter(taskDialog);

        TaskDialogController controller = loader.getController();
        controller.setMainClass(this);
        //controller.setAboutField(getName(), getAbout());
    }

    public void showSettingsDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingsDialog.fxml"));
        AnchorPane settingsDialog = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Настройки");
        dialogStage.getIcons().add(new Image("/images/settings.png"));
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(settingsDialog);
        dialogStage.setScene(scene);
        SettingsDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }

}
