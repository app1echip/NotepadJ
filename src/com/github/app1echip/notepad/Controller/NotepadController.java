package com.github.app1echip.notepad.Controller;

import com.github.app1echip.notepad.Controller.MenuBar.EditMenuController;
import com.github.app1echip.notepad.Controller.MenuBar.FileMenuController;
import com.github.app1echip.notepad.Controller.MenuBar.FormatMenuController;
import com.github.app1echip.notepad.Controller.MenuBar.ViewMenuController;
import com.github.app1echip.notepad.Controller.MenuBar.HelpMenuController;
import com.github.app1echip.notepad.Controller.StatusBar.StatusController;
import com.github.app1echip.notepad.Service.PaneProvider;
import com.github.app1echip.notepad.Service.FileStorageProvider;
import com.github.app1echip.notepad.Service.SearchProvider;
import com.github.app1echip.notepad.Service.StatusBarProvider;
import com.github.app1echip.notepad.Service.TextAreaProvider;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class NotepadController {
    private @FXML FileMenuController fileMenuController;
    private @FXML Menu fileMenu;
    private @FXML EditMenuController editMenuController;
    private @FXML Menu editMenu;
    private @FXML FormatMenuController formatMenuController;
    private @FXML Menu formatMenu;
    private @FXML ViewMenuController viewMenuController;
    private @FXML Menu viewMenu;
    private @FXML HelpMenuController helpMenuController;
    private @FXML Menu helpMenu;
    private @FXML StatusController statusBarController;
    private @FXML HBox statusBar;
    private @FXML BorderPane pane;
    private @FXML TextArea area;

    private @FXML void initialize() {
        PaneProvider.get().setBorderPane(pane);
        StatusBarProvider.get().setStatusBar(statusBar);
        TextAreaProvider.get().setTextArea(area);
        area.textProperty().addListener((l, o, n) -> SearchProvider.get().setContext(n));
        area.caretPositionProperty().addListener((l, o, n) -> SearchProvider.get().setStart(n.intValue() - 1));
        area.textProperty().bindBidirectional(FileStorageProvider.get().getDisplay());
        statusBarController.postInitialize();
        editMenuController.postInitialize();
    }
}