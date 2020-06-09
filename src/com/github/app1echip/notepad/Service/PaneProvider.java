package com.github.app1echip.notepad.Service;

import javafx.scene.layout.BorderPane;

public class PaneProvider {
    private static PaneProvider instance = new PaneProvider();

    public static PaneProvider get() {
        return instance;
    }

    private BorderPane borderPane;

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
}