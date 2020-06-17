package com.github.app1echip.notepad.controller;

import com.github.app1echip.notepad.controller.menu.EditMenuController;
import com.github.app1echip.notepad.controller.status.StatusController;
import com.github.app1echip.notepad.service.FileStorageProvider;
import com.github.app1echip.notepad.service.FontProvider;
import com.github.app1echip.notepad.service.SearchProvider;
import com.github.app1echip.notepad.service.StatusProvider;
import com.github.app1echip.notepad.service.InputHolder;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class NotepadController {
    private @FXML EditMenuController editMenuController;
    private @FXML StatusController statusBarController;
    private @FXML HBox statusBar;
    private @FXML BorderPane pane;
    private @FXML TextArea area;

    private @FXML void initialize() {
        InputHolder.get().register(area);
        FileStorageProvider.get().load(FileStorageProvider.get().getFile());
        statusBarController.postInitialize();
        editMenuController.postInitialize();
        FontProvider.get().fontFace = area.getFont().getFamily();
        FontProvider.get().fontSize.set(area.getFont().getSize());
        StatusProvider.get().register(pane, statusBar);
        SearchProvider.get().startProperty().bind(area.caretPositionProperty());
        area.textProperty().addListener((l, o, n) -> SearchProvider.get().setDirty(true));
    }
}