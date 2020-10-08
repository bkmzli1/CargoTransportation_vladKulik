package com.e.util;

import com.e.Main;
import com.e.controller.MainController;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class Table {
    String id, name, dateIn, dateOut, description;
    Button delete = new Button("Удалить");

    public Table(String id, String name, String dateIn, String dateOut, String description) {
        this.id = id;
        this.name = name;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.description = description;
        delete.setOnMouseClicked(event -> delete());
        delete.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(delete, Priority.ALWAYS);
    }

    public Table(String name, String dateIn, String dateOut, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.description = description;
        delete.setOnMouseClicked(event -> delete());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void delete() {
        MainController.list.remove(this);
        try {
            Statement statmt = Main.db.getConn().createStatement();
            statmt.execute("DELETE FROM orders WHERE id = '" + id + "' ");
            statmt.execute("DELETE FROM date WHERE id = '" + id + "' ");
            statmt.execute("DELETE FROM description WHERE id = '" + id + "' ");
            statmt.execute("DELETE FROM name WHERE id = '" + id + "' ");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {

        this.delete = delete;
    }
}
