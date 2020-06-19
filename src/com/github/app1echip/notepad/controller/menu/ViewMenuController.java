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
        statusBarCheckMenuItem.selectedProperty().addListener((l, o, n) -> StatusProvider.get().setShow(n));
        restoreDefaultZoomItem.setOnAction(e -> {
            FontProvider.get().scaleProperty().set(5);
        });
        zoomInMenuItem
                .setOnAction(e -> FontProvider.get().scaleProperty().set(FontProvider.get().scaleProperty().get() + 1));
        zoomOutMenuItem.setOnAction(e -> {
            if (FontProvider.get().scaleProperty().get() > 1)   
                FontProvider.get().scaleProperty().set(FontProvider.get().scaleProperty().get() - 1);
        });
    }
}