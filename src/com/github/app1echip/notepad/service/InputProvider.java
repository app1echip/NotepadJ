package com.github.app1echip.notepad.service;

import javafx.scene.control.TextArea;

public class InputProvider {
    private static InputProvider instance = new InputProvider();

    public static InputProvider get() {
        return instance;
    }

    private TextArea textArea;

    public TextArea text() {
        return textArea;
    }

    public void register(TextArea textArea) {
        this.textArea = textArea;
    }
}