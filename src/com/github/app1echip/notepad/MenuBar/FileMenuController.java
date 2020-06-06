package com.github.app1echip.notepad.MenuBar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.github.app1echip.notepad.NotepadController;
import com.github.app1echip.notepad.Prompt.AskSavePrompt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class FileMenuController {
    private @FXML MenuItem newMenuItem;
    private @FXML MenuItem newWindowMenuItem;
    private @FXML MenuItem openMenuItem;
    private @FXML MenuItem saveMenuItem;
    private @FXML MenuItem saveAsMenuItem;
    private @FXML MenuItem pageSetupMenuItem;
    private @FXML MenuItem printMenuItem;
    private @FXML MenuItem exitMenuItem;
    private NotepadController parent;

    public void postInitialize(NotepadController parent) {
        this.parent = parent;
    }

    private @FXML void newOnAction(ActionEvent event) {
        if (parent.dirty()) {
            switch (new AskSavePrompt(parent.file).get()) {
                case SAVE:
                    saveOnAction(null);
                    break;
                case DONT:
                    break;
                case CANCEL:
                    return;
            }
        }
        parent.buffer = "";
        parent.textArea.setText("");
        parent.file = null;
    }

    private @FXML void newWindowOnAction(ActionEvent event) {
        // TODO: call com.github.app1echip.notepad.App
    }

    private @FXML void openOnAction(ActionEvent event) {
        if (parent.dirty()) {
            switch (new AskSavePrompt(parent.file).get()) {
                case SAVE:
                    saveOnAction(null);
                    break;
                case DONT:
                    break;
                case CANCEL:
                    return;
            }
        }
        File file = new FileChooser().showOpenDialog(parent.stage);
        if (file == null)
            return;
        parent.file = file;
        parent.load();
    }

    private @FXML void saveOnAction(ActionEvent event) {
        if (parent.file == null) {
            File file = new FileChooser().showSaveDialog(parent.stage);
            if (file == null)
                return;
            parent.file = file;
        }
        try (FileWriter writer = new FileWriter(parent.file)) {
            parent.buffer = parent.textArea.getText();
            writer.write(parent.buffer);
        } catch (IOException exception) {
            // TODO: handle exception
        }
    }

    private @FXML void saveAsOnAction(ActionEvent event) {
        File file = new FileChooser().showSaveDialog(parent.stage);
        if (file == null)
            return;
        parent.file = file;
        try (FileWriter writer = new FileWriter(parent.file)) {
            parent.buffer = parent.textArea.getText();
            writer.write(parent.buffer);
        } catch (IOException exception) {
            // TODO: handle exception
        }
    }

    private @FXML void exitOnClick(ActionEvent event) {
        // TODO: call exit
    }
}