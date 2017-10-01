package com.github.youtube_analysis.Controllers;

import com.github.youtube_analysis.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by Vitaly Kurotkin on 01.10.17.
 */
public class MainDialogController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField aboutField;

    @FXML
    private void initialize() {
        //nameField.setText("hjb");
        //nameField.setText(Main.getAbout());

        // Заполнение таблицы
        //titleColumn.cellFactoryProperty(cell -> cell.getValue().)
    }

}
