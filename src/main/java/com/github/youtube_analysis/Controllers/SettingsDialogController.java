package com.github.youtube_analysis.Controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Vitaly Kurotkin on 04.10.2017.
 */
public class SettingsDialogController {
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleOk() {
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
