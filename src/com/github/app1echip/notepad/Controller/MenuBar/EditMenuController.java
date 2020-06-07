package com.github.app1echip.notepad.Controller.MenuBar;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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
    private TextArea textArea;

    public void postInitialize(TextArea text) {
        undoMenuItem.setOnAction(e -> text.undo());
        undoMenuItem.disableProperty().bind(text.undoableProperty().not());
        cutMenuItem.setOnAction(e -> text.cut());
        copyMenuItem.setOnAction(e -> text.copy());
        pasteMenuItem.setOnAction(e -> text.paste());
        deleteMenuItem.setOnAction(e -> text.deleteText(text.getSelection()));

        selectAllMenuItem.setOnAction(e -> text.selectAll());
        textArea = text;
    }

    public @FXML void findOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Prompt/Find.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

    }
}