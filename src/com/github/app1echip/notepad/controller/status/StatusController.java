package com.github.app1echip.notepad.controller.status;

import com.github.app1echip.notepad.service.FileStorageProvider;
import com.github.app1echip.notepad.service.FontProvider;
import com.github.app1echip.notepad.service.StatusProvider;
import com.github.app1echip.notepad.service.InputHolder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatusController {
    private @FXML Label lineColumnLabel;
    private @FXML Label zoomLevelLabel;
    private @FXML Label lineSeparatorLabel;
    private @FXML Label encodingLabel;

    public void postInitialize() {
        InputHolder.get().text().caretPositionProperty().addListener((l, o, n) -> {
            int[] cord = StatusProvider.get().getLnCol();
            lineColumnLabel.setText(String.format("Ln %d, Col %d", cord[0], cord[1]));
        });

    }

    private @FXML void initialize() {
        lineSeparatorLabel.setText(FileStorageProvider.get().sep.equals("\n") ? "Unix (LF)" : "Windows (CRLF)");
        FontProvider.get().scale
                .addListener((l, o, n) -> zoomLevelLabel.setText(String.format("%d%%", (int) (n.doubleValue() * 100))));
        encodingLabel.setText(System.getProperty("file.encoding"));
    }
}