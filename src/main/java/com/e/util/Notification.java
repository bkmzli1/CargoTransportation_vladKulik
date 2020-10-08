package com.e.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.e.util.Decoder.UTFtoWin1251;

public class Notification {
    public static final Logger logger = LogManager.getLogger();

    public Notification(String name, String info, Stage stage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                logger.info(UTFtoWin1251(name + ":" + info));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(name);
                alert.initOwner(stage);
                alert.setHeaderText(null);
                alert.setContentText(info);
                alert.showAndWait();
            }
        });
    }

    public Notification(String name, String info, Alert.AlertType alert, Stage stage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                logger.info(UTFtoWin1251(name + ":" + info + ":" + alert));
                Alert nAlert = new Alert(alert);
                nAlert.initOwner(stage);
                nAlert.setTitle(name);
                nAlert.setHeaderText(null);
                nAlert.setContentText(info);
                nAlert.showAndWait();
            }
        });

    }
}
