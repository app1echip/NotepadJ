package com.github.app1echip.notepad.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FileStorageProvider {
    private static FileStorageProvider instance = new FileStorageProvider();

    public static FileStorageProvider get() {
        return instance;
    }

    private StringProperty display = new SimpleStringProperty("");
    private String buffer = "";
    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void flush() {
        buffer = "";
        display.setValue(buffer);
    }

    public StringProperty getDisplay() {
        return display;
    }

    public boolean dirty() {
        return !display.getValue().equals(buffer);
    }

    public void loadFromFile() {
        if (file != null)
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                buffer = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                display.setValue(buffer);
            } catch (Exception e) {
                // TODO: handle exception
            }
        else
            flush();
    }

    public void saveToFile() {
        if (file != null)
            try (FileWriter writer = new FileWriter(file)) {
                buffer = display.getValue();
                writer.write(buffer);
            } catch (IOException exception) {
                // TODO: handle exception
            }
    }
}