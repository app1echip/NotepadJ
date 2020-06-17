package com.github.app1echip.notepad.service;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class FontProvider {
    private static FontProvider instance = new FontProvider();

    private FontProvider() {
        fontFace.addListener((l, o, n) -> updateFont());
        fontSize.addListener((l, o, n) -> updateFont());
        scale.addListener((l, o, n) -> updateFont());
    }

    public static FontProvider get() {
        return instance;
    }

    private StringProperty fontFace = new SimpleStringProperty(Font.getDefault().getFamily());
    private DoubleProperty fontSize = new SimpleDoubleProperty(Font.getDefault().getSize());
    private DoubleProperty scale = new SimpleDoubleProperty(1.0);

    public StringProperty fontFaceProperty() {
        return fontFace;
    }

    public DoubleProperty fontSizeProperty() {
        return fontSize;
    }

    public DoubleProperty scaleProperty() {
        return scale;
    }

    private void updateFont() {
        TextArea area = InputProvider.get().text();
        area.setFont(Font.font(fontFace.get(), fontSize.get() * scale.get()));
    }
}