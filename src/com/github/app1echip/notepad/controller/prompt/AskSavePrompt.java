package com.github.app1echip.notepad.controller.prompt;

import java.io.File;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AskSavePrompt {
    private String file;

    public ANSWER get() {
        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Notepad");
        alert.setHeaderText(null);
        alert.setContentText(String.format("Do you want to save changes to%s?", file));
        ButtonType saveButtonType = new ButtonType("Save");
        ButtonType doNotSaveButtonType = new ButtonType("Don't Save");
        ButtonType cancelButtonType = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(saveButtonType, doNotSaveButtonType, cancelButtonType);
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == saveButtonType)
            return ANSWER.SAVE;
        else if (option.get() == doNotSaveButtonType)
            return ANSWER.DONT;
        else
            return ANSWER.CANCEL;
    }

    public AskSavePrompt(File file) {
        this.file = file == null ? " Untitled" : "\n" + file.toString();
    }

    public enum ANSWER {
        SAVE, DONT, CANCEL
    }
}