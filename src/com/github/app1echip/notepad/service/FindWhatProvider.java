package com.github.app1echip.notepad.service;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class FindWhatProvider {
    private static FindWhatProvider instanece = new FindWhatProvider();

    public static FindWhatProvider get() {
        return instanece;
    }

    private TextField field;
    private CheckBox matchCase;
    private CheckBox wrapAround;

    public void setField(TextField field) {
        this.field = field;
    }

    public TextField getField() {
        return field;
    }

    public void setMatchCase(CheckBox matchCase) {
        this.matchCase = matchCase;
    }

    public CheckBox getMatchCase() {
        return matchCase;
    }

    public void setWrapAround(CheckBox wrapAround) {
        this.wrapAround = wrapAround;
    }

    public CheckBox getWrapAround() {
        return wrapAround;
    }

}