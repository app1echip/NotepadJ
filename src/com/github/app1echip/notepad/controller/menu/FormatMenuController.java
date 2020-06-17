package com.github.app1echip.notepad.controller.menu;

import java.io.IOException;

import com.github.app1echip.notepad.service.TextAreaProvider;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class FormatMenuController {
    private @FXML CheckMenuItem wordWrapCheckMenuItem;
    private @FXML MenuItem fontMenuItem;
    private Stage fontSelector = new Stage();

    private @FXML void initialize() {
        wordWrapCheckMenuItem.selectedProperty()
                .addListener((l, o, n) -> TextAreaProvider.get().getTextArea().setWrapText(n));
        fontMenuItem.setOnAction(e -> fontSelector.show());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Layout/Prompt/FontSelector.fxml"));
            fontSelector.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
}