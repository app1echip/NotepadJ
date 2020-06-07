package com.github.app1echip.notepad.Controller.MenuBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

import com.github.app1echip.notepad.Controller.Prompt.AskSavePrompt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileMenuController {
    private @FXML MenuItem newMenuItem;
    private @FXML MenuItem newWindowMenuItem;
    private @FXML MenuItem openMenuItem;
    private @FXML MenuItem saveMenuItem;
    private @FXML MenuItem saveAsMenuItem;
    private @FXML MenuItem pageSetupMenuItem;
    private @FXML MenuItem printMenuItem;
    private @FXML MenuItem exitMenuItem;
    private String buffer = "";
    private TextArea text;
    private File file;
    private Stage stage;

    public void postInitialize(File file, TextArea text, Stage stage) {
        this.text = text;
        this.stage = stage;
        load(file);
    }

    private @FXML void newOnAction(ActionEvent event) {
        if (!buffer.equals(text.getText())) {
            switch (new AskSavePrompt(file).get()) {
                case SAVE:
                    saveOnAction(null);
                    break;
                case DONT:
                    break;
                case CANCEL:
                    return;
            }
        }
        buffer = "";
        text.setText("");
        file = null;
    }

    private @FXML void newWindowOnAction(ActionEvent event) {
        // TODO: call com.github.app1echip.notepad.Controller.App
        try {
            Runtime.getRuntime().exec(
                    "cd /home/hf/Code/NotepadJ ; /opt/jdk8u251/bin/java -Dfile.encoding=UTF-8 -cp /home/hf/Code/NotepadJ/bin com.github.app1echip.notepad.Controller.App");
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    private @FXML void openOnAction(ActionEvent event) {
        if (!buffer.equals(text.getText())) {
            switch (new AskSavePrompt(file).get()) {
                case SAVE:
                    saveOnAction(null);
                    break;
                case DONT:
                    break;
                case CANCEL:
                    return;
            }
        }
        File file = new FileChooser().showOpenDialog(stage);
        if (file == null)
            return;
        load(file);
    }

    private @FXML void saveOnAction(ActionEvent event) {
        if (file == null) {
            File file = new FileChooser().showSaveDialog(stage);
            if (file == null)
                return;
            this.file = file;
        }
        try (FileWriter writer = new FileWriter(file)) {
            buffer = text.getText();
            writer.write(buffer);
        } catch (IOException exception) {
            // TODO: handle exception
        }
    }

    private @FXML void saveAsOnAction(ActionEvent event) {
        File file = new FileChooser().showSaveDialog(stage);
        if (file == null)
            return;
        this.file = file;
        try (FileWriter writer = new FileWriter(file)) {
            buffer = text.getText();
            writer.write(buffer);
        } catch (IOException exception) {
            // TODO: handle exception
        }
    }

    private @FXML void exitOnClick(ActionEvent event) {
        // TODO: call exit
    }

    public void load(File file) {
        if (file == null)
            stage.setTitle("Untitled");
        else
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                buffer = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                text.setText(buffer);
                stage.setTitle(file.toString());
            } catch (Exception e) {
                // TODO: handle exception
            }
    }

    public void save(File file) {

    }
}