package com.github.app1echip.notepad.controller.prompt;

import com.github.app1echip.notepad.service.SearchProvider;
import com.github.app1echip.notepad.service.SearchProvider.SWITCH;

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
        findWhatTextField.textProperty().bindBidirectional(SearchProvider.get().queryProperty());
        replaceWithTextField.textProperty().bindBidirectional(SearchProvider.get().replacerProperty());
        matchCaseCheckBox.selectedProperty().bindBidirectional(SearchProvider.get().matchCaseProperty());
        wrapAroundCheckBox.selectedProperty().bindBidirectional(SearchProvider.get().wrapAroundProperty());
        findNextButton.setOnAction(e -> SearchProvider.get().find(SWITCH.NEXT));
        replaceButton.setOnAction(e -> SearchProvider.get().find(SWITCH.NEXT, true));
        replaceAllButton.setOnAction(e -> SearchProvider.get().replaceAll());
    }
}