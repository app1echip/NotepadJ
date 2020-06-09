package com.github.app1echip.notepad.Controller.Prompt;

import com.github.app1echip.notepad.Service.FindWhatProvider;
import com.github.app1echip.notepad.Service.SearchProvider;
import com.github.app1echip.notepad.Service.TextAreaProvider;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class ReplacePromptController {
    private @FXML TextField findWhatTextField;
    private @FXML TextField replaceWithTextField;
    private @FXML Button findNextButton;
    private @FXML Button replaceButton;
    private @FXML Button replaceAllButton;
    private @FXML Button cancelButton;
    private @FXML CheckBox matchCaseCheckBox;
    private @FXML CheckBox wrapAroundCheckBox;

    private @FXML void initialize() {
        FindWhatProvider option = FindWhatProvider.get();
        findWhatTextField.textProperty().bindBidirectional(option.getField().textProperty());
        matchCaseCheckBox.selectedProperty().bindBidirectional(option.getMatchCase().selectedProperty());
        wrapAroundCheckBox.selectedProperty().bindBidirectional(option.getWrapAround().selectedProperty());
        findNextButton.setOnAction((e) -> {
            int index = SearchProvider.get().findNext();
            int length = SearchProvider.get().getQuery().length();
            if (index != -1) {
                TextAreaProvider.get().getTextArea().selectRange(index, index + length);
                SearchProvider.get().setClean();
            }
        });
        replaceButton.setOnAction(e -> {
            int index = SearchProvider.get().findNext();
            int length = SearchProvider.get().getQuery().length();
            if (index != -1) {
                TextAreaProvider area = TextAreaProvider.get();
                area.getTextArea().replaceText(index, index + length, replaceWithTextField.getText());
                area.getTextArea().selectRange(index, index + length);
                SearchProvider.get().setClean();
            }
        });

        replaceAllButton.setOnAction(e -> {
            TextAreaProvider area = TextAreaProvider.get();
            String replaced = area.getTextArea().getText().replaceAll(findWhatTextField.getText(),
                    replaceWithTextField.getText());
            area.getTextArea().setText(replaced);
        });
    }
}