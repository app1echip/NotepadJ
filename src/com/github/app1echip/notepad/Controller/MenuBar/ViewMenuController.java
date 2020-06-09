package com.github.app1echip.notepad.Controller.MenuBar;

import com.github.app1echip.notepad.Service.PaneProvider;
import com.github.app1echip.notepad.Service.StatusBarProvider;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;

public class ViewMenuController {
    private @FXML MenuItem zoomInMenuItem;
    private @FXML MenuItem zoomOutMenuItem;
    private @FXML MenuItem restoreDefaultZoomItem;
    private @FXML CheckMenuItem statusBarCheckMenuItem;

    private @FXML void initialize() {
        statusBarCheckMenuItem.selectedProperty().addListener((l, o, n) -> PaneProvider.get().getBorderPane()
                .setBottom(n ? StatusBarProvider.get().getStatusBar() : null));
    }
}