package com.github.app1echip.notepad.Controller.MenuBar;

import java.io.File;
import java.io.IOException;

import com.github.app1echip.notepad.Controller.Prompt.AskSavePrompt;
import com.github.app1echip.notepad.Service.FileStorageProvider;
import com.github.app1echip.notepad.Service.StageProvider;

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
    private @FXML MenuItem exitMenuItem;

    private @FXML void newOnAction(ActionEvent event) {
        if (FileStorageProvider.get().dirty()) {
            switch (new AskSavePrompt(FileStorageProvider.get().getFile()).get()) {
                case SAVE:
                    saveOnAction(null);
                    break;
                case DONT:
                    break;
                case CANCEL:
                    return;
            }
        }
        FileStorageProvider.get().setFile(null);
        FileStorageProvider.get().loadFromFile();
    }

    private @FXML void newWindowOnAction(ActionEvent event) {
        // TODO: call com.github.app1echip.notepad.Controller.App
        try {
            Runtime.getRuntime().exec(
                    "cd /home/hf/Code/NotepadJ ; /opt/jdk8u251/bin/java -Dfile.encoding=UTF-8 -cp /home/hf/Code/NotepadJ/bin com.github.app1echip.notepad.Controller.App");
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    private @FXML void openOnAction(ActionEvent event) {
        FileStorageProvider storage = FileStorageProvider.get();
        if (storage.dirty()) {
            switch (new AskSavePrompt(storage.getFile()).get()) {
                case SAVE:
                    saveOnAction(null);
                    break;
                case DONT:
                    break;
                case CANCEL:
                    return;
            }
        }
        File file = new FileChooser().showOpenDialog(StageProvider.get().getStage());
        storage.setFile(file);
        storage.loadFromFile();
    }

    private @FXML void saveOnAction(ActionEvent event) {
        FileStorageProvider storage = FileStorageProvider.get();
        if (storage.getFile() == null) {
            File file = new FileChooser().showSaveDialog(StageProvider.get().getStage());
            storage.setFile(file);
        }
        storage.saveToFile();
    }

    private @FXML void saveAsOnAction(ActionEvent event) {
        FileStorageProvider storage = FileStorageProvider.get();
        File file = new FileChooser().showSaveDialog(StageProvider.get().getStage());
        storage.setFile(file);
        storage.saveToFile();
    }

    private @FXML void exitOnClick(ActionEvent event) {
        // TODO: call exit
    }
}