package com.github.app1echip.notepad.Controller.StatusBar;

import com.github.app1echip.notepad.Service.TextAreaProvider;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatusController {
    private @FXML Label lineColumnLabel;
    private @FXML Label zoomLevelLabel;
    private @FXML Label lineSeparatorLabel;
    private @FXML Label encodingLabel;

    private void updateLineColumn(String content, int caret) {
        int ln = 1, col = caret + 1, id = content.indexOf(System.lineSeparator());
        while (id < caret && id != -1) {
            col = caret - id;
            id = content.indexOf(System.lineSeparator(), id + 1);
            ln++;
        }
        lineColumnLabel.setText(String.format("Ln %d, Col %d;", ln, col));
    }

    public void postInitialize() {
        TextAreaProvider area = TextAreaProvider.get();
        area.getTextArea().caretPositionProperty()
                .addListener((l, o, n) -> updateLineColumn(area.getTextArea().getText(), n.intValue()));
    }
}