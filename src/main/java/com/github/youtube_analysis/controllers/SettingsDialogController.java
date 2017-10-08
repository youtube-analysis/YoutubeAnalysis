package com.github.youtube_analysis.controllers;

import com.github.youtube_analysis.Main;
import com.github.youtube_analysis.model.Settings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Vitaly Kurotkin on 04.10.2017.
 */
public class SettingsDialogController {
    private Stage dialogStage;
    private Main mainClass;

    @FXML
    public CheckBox saveCachCheckBox;

    @FXML
    private CheckBox showTimeCheckBox;

    @FXML
    private TextField urlOfCashTextField;

    @FXML
    private void initialize() {
    }

    public void setSetting(){
        saveCachCheckBox.setSelected(mainClass.settings.isSaveCach());
        showTimeCheckBox.setSelected(mainClass.settings.isShowTime());
        urlOfCashTextField.setText(mainClass.settings.getUrlOfCash());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainClass(Main mainClass) {
        this.mainClass = mainClass;
    }

    @FXML
    private void handleOk() {
        mainClass.settings.setSaveCach(saveCachCheckBox.isSelected());
        mainClass.settings.setShowTime(showTimeCheckBox.isSelected());
        mainClass.settings.setUrlOfCash(urlOfCashTextField.getText());
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
