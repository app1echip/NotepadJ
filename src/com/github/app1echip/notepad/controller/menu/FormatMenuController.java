package com.github.app1echip.notepad.controller.menu;

import com.github.app1echip.notepad.service.InputHolder;

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

    private @FXML void initialize() throws Exception {
        wordWrapCheckMenuItem.selectedProperty()
                .addListener((l, o, n) -> InputHolder.get().text().setWrapText(n));
        fontMenuItem.setOnAction(e -> fontSelector.show());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Layout/Prompt/FontSelector.fxml"));
        fontSelector.setScene(new Scene(loader.load()));
    }
}