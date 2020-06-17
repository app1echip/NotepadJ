package com.github.app1echip.notepad.service;

import javafx.scene.control.TextArea;

public class InputHolder {
    private static InputHolder instance = new InputHolder();

    public static InputHolder get() {
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