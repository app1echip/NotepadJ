package com.github.app1echip.notepad.MenuBar;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class EditMenuController {
    private @FXML MenuItem undoMenuItem;
    private @FXML MenuItem cutMenuItem;
    private @FXML MenuItem copyMenuItem;
    private @FXML MenuItem pasteMenuItem;
    private @FXML MenuItem deleteMenuItem;
    private @FXML MenuItem searchWithBingMenuItem;
    private @FXML MenuItem findMenuItem;
    private @FXML MenuItem findNextMenuItem;
    private @FXML MenuItem findPreviousMenuItem;
    private @FXML MenuItem replaceMenuItem;
    private @FXML MenuItem goToMenuItem;
    private @FXML MenuItem selectAllMenuItem;
    private @FXML MenuItem timeDateMenuItem;

    public void postInitialize(TextArea text) {
        undoMenuItem.setOnAction(e -> text.undo());
        cutMenuItem.setOnAction(e -> text.cut());
        copyMenuItem.setOnAction(e -> text.copy());
        pasteMenuItem.setOnAction(e -> text.paste());
        deleteMenuItem.setOnAction(e -> text.deleteText(text.getSelection()));
        selectAllMenuItem.setOnAction(e -> text.selectAll());
        undoMenuItem.disableProperty().bind(text.undoableProperty().not());
    }
}