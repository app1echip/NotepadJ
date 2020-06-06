package com.github.app1echip.notepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.stream.Collectors;

import com.github.app1echip.notepad.MenuBar.EditMenuController;
import com.github.app1echip.notepad.MenuBar.FileMenuController;
import com.github.app1echip.notepad.MenuBar.FormatMenuController;
import com.github.app1echip.notepad.MenuBar.ViewMenuController;
import com.github.app1echip.notepad.StatusBar.StatusController;
import com.github.app1echip.notepad.MenuBar.HelpMenuController;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NotepadController {
    private @FXML BorderPane borderPane;
    public @FXML TextArea textArea;
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

    public Stage stage;
    public File file;
    public String buffer = "";

    public void load() {
        if (file == null)
            stage.setTitle("Untitled");
        else
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                buffer = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                textArea.setText(buffer);
                stage.setTitle(file.toString());
            } catch (Exception e) {
                // TODO: handle exception
            }
    }

    public boolean dirty() {
        return !buffer.equals(textArea.getText());
    }

    @FXML
    public void initialize() {
        fileMenuController.postInitialize(this);
        editMenuController.postInitialize(textArea);
        formatMenuController.postInitialize(textArea);
        statusHBoxController.postInitialize(textArea);
        viewMenuController.postInitialize(borderPane, statusHBox);
    }
}