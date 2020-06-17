package com.github.app1echip.notepad.controller.menu;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.app1echip.notepad.service.SearchProvider;
import com.github.app1echip.notepad.service.SearchProvider.SWITCH;
import com.github.app1echip.notepad.service.InputHolder;

import javafx.event.ActionEvent;
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
        undoMenuItem.setOnAction(e -> InputHolder.get().text().undo());
        redoMenuItem.setOnAction(e -> InputHolder.get().text().redo());
        cutMenuItem.setOnAction(e -> InputHolder.get().text().cut());
        copyMenuItem.setOnAction(e -> InputHolder.get().text().copy());
        pasteMenuItem.setOnAction(e -> InputHolder.get().text().paste());
        deleteMenuItem.setOnAction(e -> InputHolder.get().text().deleteText(InputHolder.get().text().getSelection()));
        selectAllMenuItem.setOnAction(e -> InputHolder.get().text().selectAll());
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
    }

    public @FXML void timeDateOnAction(ActionEvent event) {
        int caret = InputHolder.get().text().getCaretPosition();
        SimpleDateFormat format = new SimpleDateFormat("H:mm M/d/y");
        Date timeDate = new Date(System.currentTimeMillis());
        InputHolder.get().text().insertText(caret, format.format(timeDate));
    }

    public void postInitialize() {
        undoMenuItem.disableProperty().bind(InputHolder.get().text().undoableProperty().not());
        redoMenuItem.disableProperty().bind(InputHolder.get().text().redoableProperty().not());
        InputHolder.get().text().textProperty().addListener((l, o, n) -> findMenuItem.setDisable(n.length() == 0));
        InputHolder.get().text().selectionProperty().addListener((l, o, n) -> {
            cutMenuItem.setDisable(n.getLength() == 0);
            copyMenuItem.setDisable(n.getLength() == 0);
            deleteMenuItem.setDisable(n.getLength() == 0);
        });
    }
}