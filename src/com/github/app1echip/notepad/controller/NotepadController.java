package com.github.app1echip.notepad.controller;

import com.github.app1echip.notepad.controller.menu.EditMenuController;
// import com.github.app1echip.notepad.controller.menu.FileMenuController;
// import com.github.app1echip.notepad.controller.menu.FormatMenuController;
// import com.github.app1echip.notepad.controller.menu.HelpMenuController;
// import com.github.app1echip.notepad.controller.menu.ViewMenuController;
import com.github.app1echip.notepad.controller.status.StatusController;
import com.github.app1echip.notepad.service.FileStorageProvider;
import com.github.app1echip.notepad.service.FontProvider;
import com.github.app1echip.notepad.service.SearchProvider;
import com.github.app1echip.notepad.service.StatusProvider;
import com.github.app1echip.notepad.service.TextAreaProvider;

import javafx.fxml.FXML;
// import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class NotepadController {
    // private @FXML FileMenuController fileMenuController;
    // private @FXML Menu fileMenu;
    private @FXML EditMenuController editMenuController;
    // private @FXML Menu editMenu;
    // private @FXML FormatMenuController formatMenuController;
    // private @FXML Menu formatMenu;
    // private @FXML ViewMenuController viewMenuController;
    // private @FXML Menu viewMenu;
    // private @FXML HelpMenuController helpMenuController;
    // private @FXML Menu helpMenu;
    private @FXML StatusController statusBarController;
    private @FXML HBox statusBar;
    private @FXML BorderPane pane;
    private @FXML TextArea area;

    private @FXML void initialize() {
        StatusProvider.get().setPane(pane);
        StatusProvider.get().setStatusBar(statusBar);
        area.textProperty().addListener((l, o, n) -> SearchProvider.get().setContext(n));
        area.caretPositionProperty().addListener((l, o, n) -> SearchProvider.get().setStart(n.intValue() - 1));
        area.textProperty().bindBidirectional(FileStorageProvider.get().getDisplay());
        TextAreaProvider.get().setTextArea(area);
        statusBarController.postInitialize();
        editMenuController.postInitialize();
        FontProvider.get().fontFace = area.getFont().getFamily();
        FontProvider.get().fontSize = area.getFont().getSize();
    }
}