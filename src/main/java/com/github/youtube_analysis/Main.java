package com.github.youtube_analysis;

import com.github.youtube_analysis.controllers.MainDialogController;
import com.github.youtube_analysis.controllers.SettingsDialogController;
import com.github.youtube_analysis.controllers.TaskDialogController;
import com.github.youtube_analysis.model.Channal;
import com.github.youtube_analysis.model.Settings;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public Stage primaryStage;
    private BorderPane mainLayout;
    private Scene scene;
    private AnchorPane rootLayout;

    public ObservableList<Channal> channalsData = FXCollections.observableArrayList();
    public Settings settings;

    public static String getName(){
        return "Youtube Analysis ver.0.0.1";
    }

    public static String getAbout(){
        return "Приложение для анализа YouTube каналов, сбора статистики " +
                "и сравнения каналов \nпо различным пользовательским запросам.\n";
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

        settings = new Settings("/", false, false);
    }

    public ObservableList<Channal> getChannalsData() {
        return channalsData;
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
        controller.setMainClass(this);
        controller.setDialogStage(dialogStage);
        controller.setSetting();
        dialogStage.showAndWait();
    }

    public void goHyberlink(){
        getHostServices().showDocument("https://github.com/youtube-analysis");
    }

}
