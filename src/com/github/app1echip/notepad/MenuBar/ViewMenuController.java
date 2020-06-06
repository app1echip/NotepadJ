package com.github.app1echip.notepad.MenuBar;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ViewMenuController {
    private @FXML MenuItem zoomInMenuItem;
    private @FXML MenuItem zoomOutMenuItem;
    private @FXML MenuItem restoreDefaultZoomItem;
    private @FXML CheckMenuItem statusBarCheckMenuItem;

    public void postInitialize(BorderPane pane, HBox box) {
        statusBarCheckMenuItem.selectedProperty().addListener((l, o, n) -> pane.setBottom(n ? box : null));
    }
}