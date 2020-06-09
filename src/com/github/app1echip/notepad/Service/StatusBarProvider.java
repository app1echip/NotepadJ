package com.github.app1echip.notepad.Service;

import javafx.scene.layout.HBox;

public class StatusBarProvider {
    private static StatusBarProvider instance = new StatusBarProvider();

    public static StatusBarProvider get() {
        return instance;
    }

    private HBox statusBar;

    public HBox getStatusBar() {
        return statusBar;
    }

    public void setStatusBar(HBox statusBar) {
        this.statusBar = statusBar;
    }
}