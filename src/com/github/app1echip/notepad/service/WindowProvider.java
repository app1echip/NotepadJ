package com.github.app1echip.notepad.service;

import java.io.File;
import javafx.stage.Stage;

public class WindowProvider {
    private static WindowProvider instance = new WindowProvider();

    public static WindowProvider get() {
        return instance;
    }

    Stage stage;

    public void register(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public String updateTitle(File file) {
        String display = "Untitled";
        if (file != null) {
            String[] names = file.toString().split(File.separator);
            display = names[names.length - 1];
        }
        stage.setTitle(display + " - Notepad");
        return display;
    }
}