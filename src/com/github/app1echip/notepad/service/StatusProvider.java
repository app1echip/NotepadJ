package com.github.app1echip.notepad.service;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class StatusProvider {
    private static StatusProvider instance = new StatusProvider();

    public static StatusProvider get() {
        return instance;
    }

    private HBox statusBar;

    public void setStatusBar(HBox statusBar) {
        this.statusBar = statusBar;
    }

    private BorderPane pane;

    public void setPane(BorderPane pane) {
        this.pane = pane;
    }

    public void setDisplay(boolean display) {
        pane.setBottom(display ? statusBar : null);
    }
}