package com.github.app1echip.notepad.service;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class FontProvider {
    private static FontProvider instance = new FontProvider();

    public static FontProvider get() {
        return instance;
    }

    public String fontFace;
    public double fontSize;
    public double scale = 1.0;

    public void updateFont() {
        TextArea area = TextAreaProvider.get().getTextArea();
        area.setFont(Font.font(fontFace, fontSize * scale));
    }
}