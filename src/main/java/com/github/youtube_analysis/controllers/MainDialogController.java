package com.github.youtube_analysis.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Created by Vitaly Kurotkin on 01.10.17.
 */
public class MainDialogController extends MainLayoutController {
    @FXML
    private Label nameField;
    @FXML
    private Label aboutField;


    public void setAboutField(String nameField, String aboutField){
        this.nameField.setText(nameField);
        this.aboutField.setText(aboutField);
    }

    @FXML
    public void showSettingDialog() throws IOException {
        mainClass.showSettingsDialog();
    }

    //@FXML
    public void showTaskDialog() throws IOException {
        mainClass.showTaskDialog();
    }

}
