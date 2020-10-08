package com.e.util;

import com.e.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class StageCreate {
    Stage stage = new Stage();

    public StageCreate(String fail, String name) {
        create(fail, name, true, false,false);
    }

    public StageCreate(String fail, String name, boolean exit) {
        create(fail, name, exit, false,false);

    }

    public StageCreate(String fail, String name, boolean exit, boolean dialog, boolean size) {
        create(fail, name, exit, dialog,size);

    }

    private void create(String fail, String name, boolean exit, boolean dialog, boolean size) {
        System.out.println("fxml\\"+fail+".fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/"+fail+".fxml")));
        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        Parent root = loader.getRoot();
        Scene scene;
        if (dialog) {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(Main.stage);
            scene = new Scene(root);
        } else {
            scene = new Scene(root);
        }

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("css/main.css")).toExternalForm());
        stage.setScene(scene);
        stage.setTitle(name);


        InputStream inputStream = ClassLoader.class.getResourceAsStream("/img/icon.png");
        try {
            Image image = new Image(inputStream);
            stage.getIcons().add(image);
        } catch (NullPointerException ne) {
            System.out.println("icon null");
        }


        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if (exit) {
                    Platform.exit();
                }

            }
        });
        System.out.println(size);
        if (size) {
            Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
            stage.setHeight((sSize.height * 30d) / 100d);
            stage.setWidth((sSize.width * 30d) / 100d);
            stage.setMinHeight((sSize.height * 20d) / 100d);
            stage.setMinWidth((sSize.width * 20.1d) / 100d);

        }
    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
