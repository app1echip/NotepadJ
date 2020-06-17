package com.github.app1echip.notepad.service;

import javafx.scene.control.TextArea;

public class TextAreaProvider {
    private static TextAreaProvider instance = new TextAreaProvider();

    public static TextAreaProvider get() {
        return instance;
    }

    private TextArea textArea;

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}