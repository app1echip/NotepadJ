package com.github.app1echip.notepad.service;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class StatusProvider {
    private static StatusProvider instance = new StatusProvider();

    public static StatusProvider get() {
        return instance;
    }

    private HBox statusBar;
    private BorderPane pane;

    public void setShow(boolean display) {
        pane.setBottom(display ? statusBar : null);
    }

    public void register(BorderPane pane, HBox statusBar) {
        this.pane = pane;
        this.statusBar = statusBar;
    }

    public int[] getLnCol() {
        int caret = InputProvider.get().text().getCaretPosition();
        String content = InputProvider.get().text().getText();
        int ln = 1;
        int col = caret + 1;
        int id = content.indexOf(FileStorageProvider.get().sep);
        while (id < caret && id != -1) {
            col = caret - id;
            id = content.indexOf(FileStorageProvider.get().sep, id + 1);
            ln++;
        }
        return new int[] { ln, col };
    }
}