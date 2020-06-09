package com.github.app1echip.notepad.Controller.Prompt;

import com.github.app1echip.notepad.Service.FindWhatProvider;
import com.github.app1echip.notepad.Service.SearchProvider;
import com.github.app1echip.notepad.Service.TextAreaProvider;
import com.github.app1echip.notepad.Service.SearchProvider.DIRECTION;

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
    private ToggleGroup group = new ToggleGroup();

    private @FXML void initialize() {
        directionUpRadioButton.setToggleGroup(group);
        directionDownRadioButton.setToggleGroup(group);
        group.selectToggle(directionDownRadioButton);
        group.selectedToggleProperty().addListener((l, o, n) -> SearchProvider.get()
                .setDirection(n == directionDownRadioButton ? DIRECTION.DOWN : DIRECTION.UP));
        matchCaseCheckBox.selectedProperty().addListener((l, o, n) -> SearchProvider.get().setMatchCase(n));
        wrapAroundCheckBox.selectedProperty().addListener((l, o, n) -> SearchProvider.get().setWrapAround(n));
        textField.textProperty().addListener((l, o, n) -> SearchProvider.get().setQuery(n));
        findNextButton.setOnAction((e) -> {
            int index = SearchProvider.get().findNext();
            int length = SearchProvider.get().getQuery().length();
            if (index != -1) {
                TextAreaProvider.get().getTextArea().selectRange(index, index + length);
                SearchProvider.get().setClean();
            }
        });
        FindWhatProvider option = FindWhatProvider.get();
        option.setField(textField);
        option.setMatchCase(matchCaseCheckBox);
        option.setWrapAround(wrapAroundCheckBox);
    }
}