package com.github.app1echip.notepad.Controller.MenuBar;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class FormatMenuController {
    private @FXML CheckMenuItem wordWrapCheckMenuItem;
    private @FXML MenuItem fontMenuItem;

    public void postInitialize(TextArea text) {
        text.wrapTextProperty().bind(wordWrapCheckMenuItem.selectedProperty());
    }
}