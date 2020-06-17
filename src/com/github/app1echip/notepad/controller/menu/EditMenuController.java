package com.github.app1echip.notepad.controller.menu;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.app1echip.notepad.service.SearchProvider;
import com.github.app1echip.notepad.service.SearchProvider.SWITCH;
import com.github.app1echip.notepad.service.InputProvider;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class EditMenuController {
    private @FXML MenuItem undoMenuItem;
    private @FXML MenuItem redoMenuItem;
    private @FXML MenuItem cutMenuItem;
    private @FXML MenuItem copyMenuItem;
    private @FXML MenuItem pasteMenuItem;
    private @FXML MenuItem deleteMenuItem;
    private @FXML MenuItem findMenuItem;
    private @FXML MenuItem findNextMenuItem;
    private @FXML MenuItem findPreviousMenuItem;
    private @FXML MenuItem replaceMenuItem;
    private @FXML MenuItem goToMenuItem;
    private @FXML MenuItem selectAllMenuItem;
    private @FXML MenuItem timeDateMenuItem;

    private @FXML void initialize() throws Exception {
        undoMenuItem.setOnAction(e -> InputProvider.get().text().undo());
        redoMenuItem.setOnAction(e -> InputProvider.get().text().redo());
        cutMenuItem.setOnAction(e -> InputProvider.get().text().cut());
        copyMenuItem.setOnAction(e -> InputProvider.get().text().copy());
        pasteMenuItem.setOnAction(e -> InputProvider.get().text().paste());
        deleteMenuItem
                .setOnAction(e -> InputProvider.get().text().deleteText(InputProvider.get().text().getSelection()));
        selectAllMenuItem.setOnAction(e -> InputProvider.get().text().selectAll());
        Stage findPrompt = new Stage();
        findPrompt.setScene(new Scene(new FXMLLoader(getClass().getResource("/res/Layout/Prompt/Find.fxml")).load()));
        findMenuItem.setOnAction(e -> findPrompt.show());
        findNextMenuItem.setOnAction(e -> SearchProvider.get().find(SWITCH.NEXT));
        findPreviousMenuItem.setOnAction(e -> SearchProvider.get().find(SWITCH.PREV));
        Stage replacePrompt = new Stage();
        replacePrompt
                .setScene(new Scene(new FXMLLoader(getClass().getResource("/res/Layout/Prompt/Replace.fxml")).load()));
        replaceMenuItem.setOnAction(e -> replacePrompt.show());
        Stage goToPrompt = new Stage();
        goToPrompt.setScene(new Scene(new FXMLLoader(getClass().getResource("/res/Layout/Prompt/GoTo.fxml")).load()));
        goToMenuItem.setOnAction(e -> goToPrompt.show());
        SearchProvider.get().queryProperty().addListener((l, o, n) -> {
            findNextMenuItem.setDisable(n.length() == 0);
            findPreviousMenuItem.setDisable(n.length() == 0);
        });
        timeDateMenuItem.setOnAction(e -> {
            int caret = InputProvider.get().text().getCaretPosition();
            SimpleDateFormat format = new SimpleDateFormat("H:mm M/d/y");
            Date timeDate = new Date(System.currentTimeMillis());
            InputProvider.get().text().insertText(caret, format.format(timeDate));
        });
    }

    public void postInitialize() {
        undoMenuItem.disableProperty().bind(InputProvider.get().text().undoableProperty().not());
        redoMenuItem.disableProperty().bind(InputProvider.get().text().redoableProperty().not());
        InputProvider.get().text().textProperty().addListener((l, o, n) -> findMenuItem.setDisable(n.length() == 0));
        InputProvider.get().text().selectionProperty().addListener((l, o, n) -> {
            cutMenuItem.setDisable(n.getLength() == 0);
            copyMenuItem.setDisable(n.getLength() == 0);
            deleteMenuItem.setDisable(n.getLength() == 0);
        });
    }
}