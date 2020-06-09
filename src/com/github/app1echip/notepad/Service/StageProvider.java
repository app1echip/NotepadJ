package com.github.app1echip.notepad.Service;

import javafx.stage.Stage;

public class StageProvider {
    private static StageProvider instance = new StageProvider();

    public static StageProvider get() {
        return instance;
    }

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}