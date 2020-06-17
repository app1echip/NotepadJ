package com.github.app1echip.notepad.controller.prompt;

import com.github.app1echip.notepad.service.SearchProvider;
import com.github.app1echip.notepad.service.SearchProvider.DIRECTION;
import com.github.app1echip.notepad.service.SearchProvider.SWITCH;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FindPromptController {
    private @FXML TextField textField;
    private @FXML CheckBox matchCaseCheckBox;
    private @FXML CheckBox wrapAroundCheckBox;
    private @FXML RadioButton directionUpRadioButton;
    private @FXML RadioButton directionDownRadioButton;
    private @FXML Button findNextButton;
    private @FXML Button cancelButton;

    private @FXML void initialize() {
        ToggleGroup group = new ToggleGroup();
        directionUpRadioButton.setToggleGroup(group);
        directionDownRadioButton.setToggleGroup(group);
        group.selectToggle(directionDownRadioButton);
        group.selectedToggleProperty().addListener((l, o, n) -> SearchProvider.get()
                .setDirection(n == directionDownRadioButton ? DIRECTION.DW : DIRECTION.UP));
        matchCaseCheckBox.selectedProperty().bindBidirectional(SearchProvider.get().matchCaseProperty());
        wrapAroundCheckBox.selectedProperty().bindBidirectional(SearchProvider.get().wrapAroundProperty());
        textField.textProperty().bindBidirectional(SearchProvider.get().queryProperty());
        findNextButton.setOnAction(e -> SearchProvider.get().find(SWITCH.NEXT));
        cancelButton.setOnAction(e -> cancelButton.getScene().getWindow().hide());
    }
}