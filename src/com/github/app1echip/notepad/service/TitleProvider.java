package com.github.app1echip.notepad.service;

import java.io.File;

import javafx.stage.Stage;

public class TitleProvider {
    private static TitleProvider instance = new TitleProvider();

    public static TitleProvider get() {
        return instance;
    }

    Stage stage;

    public void register(Stage stage) {
        this.stage = stage;
    }

    public String update(File file) {
        String display = "Untitled";
        if (file != null) {
            String[] names = file.toString().split(File.separator);
            display = names[names.length - 1];
        }
        stage.setTitle(display + " - Notepad");
        return display;
    }
}