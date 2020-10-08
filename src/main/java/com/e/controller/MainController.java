package com.e.controller;

import com.e.Main;
import com.e.util.CustomTableView;
import com.e.util.Notification;
import com.e.util.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainController {
    public VBox VboxT;
    public TextArea text;
    public DatePicker dataOut;
    public DatePicker dataIn;
    public TextField name;

    public static CustomTableView<Table> customTableView = new CustomTableView<>();
    public static ObservableList<Table> list;

    public void initialize() throws Exception {
        list = FXCollections.observableArrayList();
        VboxT.getChildren().add(customTableView);
        col(customTableView);
        list.addAll(Main.db.getBD());
        customTableView.setItems(list);
    }

    public static void col(CustomTableView customTableView) {

        customTableView.addCol("Имя", "name");
        customTableView.addCol("Дата прибытия", "dateIn");
        customTableView.addCol("Дата отправки", "dateOut");
        customTableView.addCol("Описание", "description");
        customTableView.addCol("Удаление", "delete");
        // customTableView.addCol("Состояние", "sost");
    }

    public void Create(ActionEvent actionEvent) {
        String name = this.name.getText();
        String di = dataIn.getEditor().getText();
        String dou = dataOut.getEditor().getText();
        String t = text.getText();

        if (name.length() > 20 | name.length() < 2) {
            new Notification("Внимание", "Имя должно быть меньше 20  и не меньше 3", Main.stage);
        } else if (di.length() < 2 | dou.length() < 2) {
            new Notification("Внимание", "Не правильная дата", Main.stage);
        } else {
            Table table = new Table(name, di, dou, t);
            add(table);
        }

    }

    public void add(Table t) {
        Main.db.add(t);
        list.add(t);
        customTableView.setItems(list);
    }
}
