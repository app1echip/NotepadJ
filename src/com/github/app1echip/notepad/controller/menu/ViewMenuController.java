package com.github.app1echip.notepad.controller.menu;

import com.github.app1echip.notepad.service.FontProvider;
import com.github.app1echip.notepad.service.StatusProvider;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;

public class ViewMenuController {
    private @FXML MenuItem zoomInMenuItem;
    private @FXML MenuItem zoomOutMenuItem;
    private @FXML MenuItem restoreDefaultZoomItem;
    private @FXML CheckMenuItem statusBarCheckMenuItem;

    private @FXML void initialize() {
        statusBarCheckMenuItem.selectedProperty().addListener((l, o, n) -> StatusProvider.get().setDisplay(n));
        restoreDefaultZoomItem.setOnAction(e -> {
            FontProvider.get().scale = 1.0;
            FontProvider.get().updateFont();
        });
        zoomInMenuItem.setOnAction(e -> {
            FontProvider.get().scale += 0.2;
            FontProvider.get().updateFont();
        });
        zoomOutMenuItem.setOnAction(e -> {
            if (FontProvider.get().scale > 0.2) {
                FontProvider.get().scale -= 0.2;
                FontProvider.get().updateFont();
            }
        });
    }
}