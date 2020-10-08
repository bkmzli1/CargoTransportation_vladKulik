package com.e.util;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Set;

public class CustomTableView<S> extends TableView<S> {
    private final EventHandler<MouseEvent> consumeEvent = MouseEvent::consume;

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        final Set<Node> dragRects = lookup("TableHeaderRow").lookupAll("Rectangle");
      /*  for (Node dragRect : dragRects) {
            dragRect.removeEventFilter(MouseEvent.ANY, consumeEvent);
            dragRect.addEventFilter(MouseEvent.ANY, consumeEvent);
        }*/
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(this, Priority.ALWAYS);
        setWidth(Double.MAX_VALUE);
        setHeight(Double.MAX_VALUE);
    }


    public void addCol(String text, String property) {
        TableColumn<S, String> column = new TableColumn<S, String>(text);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        getColumns().add(column);
    }

}