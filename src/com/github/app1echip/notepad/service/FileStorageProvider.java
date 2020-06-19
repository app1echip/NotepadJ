package com.github.app1echip.notepad.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileStorageProvider {
    private static FileStorageProvider instance = new FileStorageProvider();

    public static FileStorageProvider get() {
        return instance;
    }

    public enum ANSWER {
        SAVE, DONT, CANCEL
    }

    private String buffer;
    private File file;
    public String sep = File.separator;

    public File getFile() {
        return file;
    }

    public boolean dirty() {
        return !InputProvider.get().text().getText().equals(buffer);
    }

    public void setFile(File file) {
        this.file = file;
        WindowProvider.get().updateTitle(file);
    }

    public void load(File file) {
        setFile(file);
        if (file != null) {
            sep = "\n";
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                int c = reader.read();
                while (c != -1 && c != '\r')
                    c = reader.read();
                if (c == '\r')
                    sep = "\r\n";
            } catch (Exception e) {
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                buffer = reader.lines().collect(Collectors.joining(sep));
                InputProvider.get().text().setText(buffer);
            } catch (Exception e) {
            }
        } else {
            buffer = "";
            InputProvider.get().text().clear();
        }
    }

    public void save(boolean saveAs) {
        if (saveAs || file == null) {
            file = new FileChooser().showSaveDialog(new Stage());
            setFile(file);
        }
        if (file != null)
            try (FileWriter writer = new FileWriter(file)) {
                buffer = InputProvider.get().text().getText();
                writer.write(buffer);
            } catch (IOException exception) {
            }
    }

    public ANSWER promptToSave() {
        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Notepad");
        alert.setHeaderText(null);
        alert.setContentText(String.format("Do you want to save changes to %s?",
                WindowProvider.get().updateTitle(FileStorageProvider.get().getFile())));
        ButtonType saveButtonType = new ButtonType("Save");
        ButtonType doNotSaveButtonType = new ButtonType("Don't Save");
        ButtonType cancelButtonType = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(saveButtonType, doNotSaveButtonType, cancelButtonType);
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == saveButtonType)
            return ANSWER.SAVE;
        else if (option.get() == doNotSaveButtonType)
            return ANSWER.DONT;
        else
            return ANSWER.CANCEL;
    }
}