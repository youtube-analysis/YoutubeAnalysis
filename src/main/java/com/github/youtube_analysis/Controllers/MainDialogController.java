package com.github.youtube_analysis.Controllers;

import com.github.youtube_analysis.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by Vitaly Kurotkin on 01.10.17.
 */
public class MainDialogController extends MainLayoutController {
    private Main mainClass;
    @FXML
    private Label nameField;
    @FXML
    private Label aboutField;


    public void setAboutField(String nameField, String aboutField){
        this.nameField.setText(nameField);
        this.aboutField.setText(aboutField);
    }

}
