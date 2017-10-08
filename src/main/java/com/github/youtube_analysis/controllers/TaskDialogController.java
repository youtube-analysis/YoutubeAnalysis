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
import java.util.concurrent.*;

/**
 * Created by Vitaly Kurotkin on 05.10.2017.
 */
public class TaskDialogController {
    public Main mainClass;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(4);
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
    private Label channelTitleLabel_1;
    @FXML
    private Label channelIdLabel_1;
    @FXML
    private Label subscriberCountLabel_1;
    @FXML
    private Label videoCountLabel_1;
    @FXML
    private Label viewCountLabel_1;
    @FXML
    private Label createDateLabel_1;
    @FXML
    private Label channelTitleLabel_2;
    @FXML
    private Label channelIdLabel_2;
    @FXML
    private Label subscriberCountLabel_2;
    @FXML
    private Label videoCountLabel_2;
    @FXML
    private Label viewCountLabel_2;
    @FXML
    private Label createDateLabel_2;
    @FXML
    private Label timeOfReq;
    @FXML
    private Label ReqLabel;

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
        timeOfReq.setVisible(mainClass.settings.isShowTime());
        ReqLabel.setVisible(false);
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
            notSelectFoDel();
        }
    }

    @FXML
    private void clearAll(){
        channalTableView.getItems().removeAll(channalTableView.getItems());
    }

    @FXML
    private void addNewChannal(){
        long time = System.currentTimeMillis();
        ReqLabel.setVisible(true);
        String text = searchText.getText();
        if (!text.equals("")){
            try {
                Channal channal = createChannal(text);
                if(channal == null){
                    notCorrectId();
                } else {
                    mainClass.channalsData.add(channal);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            notIdNormal();
        }
        time = System.currentTimeMillis() - time;
        timeOfReq.setText("Время запроса " + time + "ms");
        ReqLabel.setVisible(false);
    }

    @FXML
    public void searchChannals() {
        long time = System.currentTimeMillis();
        timeOfReq.setText("Загружаем ...");
        String text = searchText.getText();
        if (!text.equals("")){
            Callable call = new Callable<Long>() {
                @Override
                public Long call() {
                    try {
                        YoutubeSearcher searcher = new YoutubeSearcher();
                        ActivityResponce responce = searcher.getResult(text);
                        for (Activity activity : responce.items ){
                            String id = activity.snippet.channelId;
                            mainClass.channalsData.add(createChannal(id));
                        }
                        return System.currentTimeMillis();
                    } catch (ExecutionException e) {
                        netError();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return System.currentTimeMillis();
                }
            };
            FutureTask task = new FutureTask<Boolean>(call);
            threadPool.submit(task);
            if(mainClass.settings.isShowTime()){
                try {
                    long timeReq = (long)task.get() - time;
                    timeOfReq.setText("Время запроса " + timeReq + "ms");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } else {
            notCorrectSearch();
        }
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

    @FXML
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

    @FXML
    private void showDetails1(){
        int selectedIndex = channalTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Channal channal = channalTableView.getItems().get(selectedIndex);
            channelTitleLabel_1.setText(channal.getChannelTitle());
            channelIdLabel_1.setText(channal.getChannelId());
            subscriberCountLabel_1.setText(Long.toString(channal.getSubscriberCount()));
            videoCountLabel_1.setText(Long.toString(channal.getVideoCount()));
            viewCountLabel_1.setText(Long.toString(channal.getViewCount()));
            createDateLabel_1.setText(DateUtil.format(channal.getCreateDate()));
        } else {
            notSelect();
        }
    }

    @FXML
    private void showDetails2(){
        int selectedIndex = channalTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Channal channal = channalTableView.getItems().get(selectedIndex);
            channelTitleLabel_2.setText(channal.getChannelTitle());
            channelIdLabel_2.setText(channal.getChannelId());
            subscriberCountLabel_2.setText(Long.toString(channal.getSubscriberCount()));
            videoCountLabel_2.setText(Long.toString(channal.getVideoCount()));
            viewCountLabel_2.setText(Long.toString(channal.getViewCount()));
            createDateLabel_2.setText(DateUtil.format(channal.getCreateDate()));

        } else {
            notSelect();
        }
    }

    private void notSelect(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainClass.primaryStage);
        alert.setTitle("Не выбран канал");
        alert.setHeaderText("Не выбран канал для сравнения");
        alert.setContentText("Пожалуйста, выберите канал для сравнения.");
        alert.showAndWait();
    }

    private void notSelectFoDel(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainClass.primaryStage);
        alert.setTitle("Не выбран канал");
        alert.setHeaderText("Не выбран канал для удаления");
        alert.setContentText("Пожалуйста, выберите канал для удаления.");
        alert.showAndWait();
    }

    private void notCorrectId(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainClass.primaryStage);
        alert.setTitle("Не корректный id");
        alert.setHeaderText("Введен не корректный id");
        alert.setContentText("Пожалуйста, введите корректный id.");
        alert.showAndWait();
    }

    private void notIdNormal(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainClass.primaryStage);
        alert.setTitle("Не введен id");
        alert.setHeaderText("Не введен id канала");
        alert.setContentText("Пожалуйста, выберите id канала.");
        alert.showAndWait();
    }

    private void netError(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainClass.primaryStage);
        alert.setTitle("Сетевая ошибка");
        alert.setHeaderText("Ошибка подключения к серверу");
        alert.setContentText("Пожалуйста, убедитесь, что подключение к серверу доступно.");
        alert.showAndWait();
    }

    private void notCorrectSearch(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainClass.primaryStage);
        alert.setTitle("Нет информации для поиска");
        alert.setHeaderText("Не введена информация для поиска");
        alert.setContentText("Пожалуйста, выберите информацию для поиска.");
        alert.showAndWait();
    }
}
