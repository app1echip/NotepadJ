package com.github.app1echip.notepad.service;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class FontProvider {
    private static FontProvider instance = new FontProvider();

    private FontProvider() {
    
    }

    public static FontProvider get() {
        return instance;
    }

    public String fontFace;
    public DoubleProperty fontSize = new SimpleDoubleProperty();
    public DoubleProperty scale = new SimpleDoubleProperty(1.0);

    public void updateFont() {
        TextArea area = InputHolder.get().text();
        area.setFont(Font.font(fontFace, fontSize.get() * scale.get()));
    }
}