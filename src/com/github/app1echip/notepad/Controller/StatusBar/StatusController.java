package com.github.app1echip.notepad.Controller.StatusBar;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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

    public void postInitialize(TextArea text) {
        text.caretPositionProperty().addListener((l, o, n) -> updateLineColumn(text.getText(), n.intValue()));
    }
}