package com.github.youtube_analysis.controllers;

import com.github.youtube_analysis.Main;
import com.github.youtube_analysis.api.YoutubeSearcher;
import com.github.youtube_analysis.api.youtube.entities.Activity;
import com.github.youtube_analysis.api.youtube.entities.ActivityResponce;
import com.github.youtube_analysis.api.youtube.entities.Snippet;
import com.github.youtube_analysis.model.Channal;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Vitaly Kurotkin on 05.10.2017.
 */
public class TaskDialogController {
    public Main mainClass;

    @FXML
    public TableView<Channal> channalTableView;

    @FXML
    private TableColumn<Channal, String> nameColumn;

    @FXML
    private TableColumn<Channal, String> idColumn;

    @FXML
    private TextField searchText;

    @FXML
    private void initialize() {

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().channelTitleProperty());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().channelIdProperty());



        // Слушаем изменения выбора.
//        channalTableView.getSelectionModel()
//                .selectedItemProperty()
//                .addListener(
//                        (observable, oldValue, newValue) -> showPersonDetails(newValue)
//                );
    }

    public void setMainClass(Main mainClass) {
        this.mainClass = mainClass;
        channalTableView.setItems(this.mainClass.getChannalsData());
    }


    //@FXML
    public void showMainDialog() throws IOException {
        mainClass.showMainDialog();
    }

    @FXML
    private void deleteChannal() {
        int selectedIndex = channalTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            channalTableView.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainClass.primaryStage);
            alert.setTitle("Не выбран канал");
            alert.setHeaderText("Не выбран канал для удаления");
            alert.setContentText("Пожалуйста, выберите канал для удаления.");
            alert.showAndWait();
        }
    }

    @FXML
    private void clearAll(){
        channalTableView.getItems().removeAll(channalTableView.getItems());
    }

    @FXML
    private void addNewChannal(){
        String text = searchText.getText();
        if (!text.equals("")){
            Channal channal = new Channal("", text);
            mainClass.channalsData.add(channal);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainClass.primaryStage);
            alert.setTitle("Не введен id");
            alert.setHeaderText("Не введен id канала");
            alert.setContentText("Пожалуйста, выберите id канала.");
            alert.showAndWait();
        }
    }


    public void searchChannals() {
        String text = searchText.getText();
        if (!text.equals("")){
            try {
                YoutubeSearcher searcher = new YoutubeSearcher();
                ActivityResponce responce = searcher.getResult(text);
                for (Activity activity : responce.items ){
                    String name = activity.snippet.channelTitle;
                    String id = activity.snippet.channelId;
                    Channal channal = new Channal(name, id);
                    mainClass.channalsData.add(channal);
                }
            } catch (ExecutionException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainClass.primaryStage);
                alert.setTitle("Сетевая ошибка");
                alert.setHeaderText("Ошибка подключения к серверу");
                alert.setContentText("Пожалуйста, убедитесь, что подключение к серверу доступно.");
                alert.showAndWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainClass.primaryStage);
            alert.setTitle("Нет информации для поиска");
            alert.setHeaderText("Не введена информация для поиска");
            alert.setContentText("Пожалуйста, выберите информацию для поиска.");
            alert.showAndWait();
        }
        mainClass.channalsData.addAll();

    }
}
