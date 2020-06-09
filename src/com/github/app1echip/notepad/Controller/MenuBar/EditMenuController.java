package com.github.app1echip.notepad.Controller.MenuBar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.app1echip.notepad.Service.SearchProvider;
import com.github.app1echip.notepad.Service.TextAreaProvider;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class EditMenuController {
    private @FXML MenuItem undoMenuItem;
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
    private Stage findPrompt = new Stage();
    private Stage replacePrompt = new Stage();
    private Stage goToPrompt = new Stage();

    private @FXML void initialize() {
        undoMenuItem.setOnAction(e -> TextAreaProvider.get().getTextArea().undo());
        cutMenuItem.setOnAction(e -> TextAreaProvider.get().getTextArea().cut());
        copyMenuItem.setOnAction(e -> TextAreaProvider.get().getTextArea().copy());
        pasteMenuItem.setOnAction(e -> TextAreaProvider.get().getTextArea().paste());
        deleteMenuItem.setOnAction(e -> TextAreaProvider.get().getTextArea()
                .deleteText(TextAreaProvider.get().getTextArea().getSelection()));
        findMenuItem.setOnAction(e -> findPrompt.show());
        replaceMenuItem.setOnAction(e -> replacePrompt.show());
        goToMenuItem.setOnAction(e -> goToPrompt.show());
        selectAllMenuItem.setOnAction(e -> TextAreaProvider.get().getTextArea().selectAll());

        try {
            FXMLLoader findPromptLoader = new FXMLLoader(getClass().getResource("/res/Layout/Prompt/Find.fxml"));
            findPrompt.setScene(new Scene(findPromptLoader.load()));
            FXMLLoader replacePromptLoader = new FXMLLoader(getClass().getResource("/res/Layout/Prompt/Replace.fxml"));
            replacePrompt.setScene(new Scene(replacePromptLoader.load()));
            FXMLLoader goToPromptLoader = new FXMLLoader(getClass().getResource("/res/Layout/Prompt/GoTo.fxml"));
            goToPrompt.setScene(new Scene(goToPromptLoader.load()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    private @FXML void findNextOnAction(ActionEvent event) {
        int index = SearchProvider.get().findNext();
        int length = SearchProvider.get().getQuery().length();
        if (index != -1) {
            TextAreaProvider.get().getTextArea().selectRange(index, index + length);
            SearchProvider.get().setClean();
        }
    }

    public @FXML void findPreviousOnAction(ActionEvent event) {
        int index = SearchProvider.get().findPrevious();
        int length = SearchProvider.get().getQuery().length();
        if (index != -1) {
            TextAreaProvider.get().getTextArea().selectRange(index, index + length);
            SearchProvider.get().setClean();
        }
    }

    public @FXML void timeDateOnAction(ActionEvent event) {
        int caret = TextAreaProvider.get().getTextArea().getCaretPosition();
        SimpleDateFormat format = new SimpleDateFormat("H:mm M/d/y");
        Date timeDate = new Date(System.currentTimeMillis());
        TextAreaProvider.get().getTextArea().insertText(caret, format.format(timeDate));
    }

    public void postInitialize() {
        undoMenuItem.disableProperty().bind(TextAreaProvider.get().getTextArea().undoableProperty().not());

        TextAreaProvider.get().getTextArea().textProperty().addListener((l, o, n) -> {
            findMenuItem.setDisable(n.length() == 0);
            findNextMenuItem.setDisable(n.length() == 0 || SearchProvider.get().getQuery().length() == 0);
            findPreviousMenuItem.setDisable(n.length() == 0 || SearchProvider.get().getQuery().length() == 0);
        });
    }
}