package com.github.app1echip.notepad.controller.prompt;

import com.github.app1echip.notepad.service.InputProvider;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GoToPromptController {
    private @FXML TextField textField;
    private @FXML Button goToButton;
    private @FXML Button cancelButton;

    private @FXML void initialize() {
        goToButton.setOnAction(e -> {
            InputProvider area = InputProvider.get();
            String text = area.text().getText();
            int ln = 1;
            try {
                String input = textField.getText();
                ln = Integer.parseInt(input);
            } catch (Exception exception) {
            }
            int i;
            for (i = 0; ln > 1 && i < text.length(); ++i) {
                if (text.charAt(i) == '\n')
                    ln--;
            }
            if (i != text.length()) {
                area.text().positionCaret(i);
                textField.getScene().getWindow().hide();
            }
        });
        cancelButton.setOnAction(e -> cancelButton.getScene().getWindow().hide());
    }
}