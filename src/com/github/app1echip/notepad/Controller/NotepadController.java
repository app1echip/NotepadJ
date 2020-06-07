package com.github.app1echip.notepad.Controller;

import java.io.File;

import com.github.app1echip.notepad.Controller.MenuBar.EditMenuController;
import com.github.app1echip.notepad.Controller.MenuBar.FileMenuController;
import com.github.app1echip.notepad.Controller.MenuBar.FormatMenuController;
import com.github.app1echip.notepad.Controller.MenuBar.ViewMenuController;
import com.github.app1echip.notepad.Controller.StatusBar.StatusController;
import com.github.app1echip.notepad.Controller.MenuBar.HelpMenuController;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NotepadController {
    private @FXML BorderPane borderPane;
    private @FXML TextArea textArea;
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
    private @FXML StatusController statusHBoxController;
    private @FXML HBox statusHBox;

    public void postInitialize(Stage stage, File file) {
        fileMenuController.postInitialize(file, textArea, stage);
        editMenuController.postInitialize(textArea);
        formatMenuController.postInitialize(textArea);
        statusHBoxController.postInitialize(textArea);
        viewMenuController.postInitialize(borderPane, statusHBox);
    }
}