package com.e;

import com.e.db.DB;
import com.e.util.StageCreate;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {
    public static Stage stage;
    public static DB db;
    public static String FileSeve = System.getenv("APPDATA") + "\\CT", SQLFile = FileSeve + "\\CT.db";
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() throws Exception {
        new File(FileSeve).mkdir();
        db = new DB(SQLFile);
        db.start();
    }

    @Override
    public void start(Stage stage) {
        StageCreate stageCreate = new StageCreate("main","Склад грузо перевозок");
        stageCreate.getStage().show();

        this.stage = stageCreate.getStage();

    }
}
