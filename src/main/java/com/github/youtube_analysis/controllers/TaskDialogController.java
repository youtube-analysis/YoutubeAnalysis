package com.github.youtube_analysis.controllers;

import com.github.youtube_analysis.Main;
import com.github.youtube_analysis.Utils.DateUtil;
import com.github.youtube_analysis.api.YoutubeGetInfo;
import com.github.youtube_analysis.api.YoutubeSearcher;
import com.github.youtube_analysis.api.youtube.channal.InfoResponce;
import com.github.youtube_analysis.api.youtube.entities.Activity;
import com.github.youtube_analysis.api.youtube.entities.ActivityResponce;
import com.github.youtube_analysis.model.Channal;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
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
    private Label channelTitleLabel;

    @FXML
    private Label channelIdLabel;

    @FXML
    private Label subscriberCountLabel;

    @FXML
    private Label videoCountLabel;

    @FXML
    private Label viewCountLabel;

    @FXML
    private Label createDateLabel;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().channelTitleProperty());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().channelIdProperty());

        channalTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> showDetails(newValue)
                );
    }

    public void setMainClass(Main mainClass) {
        this.mainClass = mainClass;
        channalTableView.setItems(this.mainClass.getChannalsData());
    }


    @FXML
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
            try {
                Channal channal = createChannal(text);
                if(channal == null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(mainClass.primaryStage);
                    alert.setTitle("Не корректный id");
                    alert.setHeaderText("Введен не корректный id");
                    alert.setContentText("Пожалуйста, введите корректный id.");
                    alert.showAndWait();
                } else {
                    mainClass.channalsData.add(channal);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                    String id = activity.snippet.channelId;
                    mainClass.channalsData.add(createChannal(id));
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

    private Channal createChannal(String id) throws ExecutionException, InterruptedException {
        YoutubeGetInfo getterInfo = new YoutubeGetInfo();
        InfoResponce info = getterInfo.getChannalInfo(id);
        if(info.items.size() == 0){
            return null;
        }
        String name = info.items.get(0).snippet.title;
        long subscriberCount = Long.parseLong(info.items.get(0).statistics.subscriberCount);
        long videoCount = Long.parseLong(info.items.get(0).statistics.videoCount);
        long viewCount = Long.parseLong(info.items.get(0).statistics.viewCount);
        Calendar createDate = info.items.get(0).snippet.publishedAt;

        return new Channal(name, id, subscriberCount, videoCount, viewCount, createDate);
    }

    private void showDetails(Channal channal){
        if (channal != null) {
            channelTitleLabel.setText(channal.getChannelTitle());
            channelIdLabel.setText(channal.getChannelId());
            subscriberCountLabel.setText(Long.toString(channal.getSubscriberCount()));
            videoCountLabel.setText(Long.toString(channal.getVideoCount()));
            viewCountLabel.setText(Long.toString(channal.getViewCount()));
            createDateLabel.setText(DateUtil.format(channal.getCreateDate()));
        } else {
            channelTitleLabel.setText("");
            channelIdLabel.setText("");
            subscriberCountLabel.setText("");
            videoCountLabel.setText("");
            viewCountLabel.setText("");
            createDateLabel.setText("");
        }
    }
}
