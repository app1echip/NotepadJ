package com.github.app1echip.notepad.service;

import java.util.ArrayList;
import java.util.Collections;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchProvider {
    private static SearchProvider instance = new SearchProvider();

    private SearchProvider() {
        matchCase.addListener((l, o, n) -> dirty = true);
        wrapAround.addListener((l, o, n) -> dirty = true);
        query.addListener((l, o, n) -> dirty = true);
        start.addListener((l, o, n) -> dirty = true);
    }

    public static SearchProvider get() {
        return instance;
    }

    public enum DIRECTION {
        UP, DW
    }

    public enum SWITCH {
        NEXT, PREV
    }

    private int nowAt = -1;

    private DIRECTION direction = DIRECTION.DW;
    private BooleanProperty matchCase = new SimpleBooleanProperty();
    private BooleanProperty wrapAround = new SimpleBooleanProperty();
    private StringProperty query = new SimpleStringProperty("");
    private IntegerProperty start = new SimpleIntegerProperty();
    private StringProperty replacer = new SimpleStringProperty(new String());

    public BooleanProperty matchCaseProperty() {
        return matchCase;
    }

    public BooleanProperty wrapAroundProperty() {
        return wrapAround;
    }

    public StringProperty queryProperty() {
        return query;
    }

    public StringProperty replacerProperty() {
        return replacer;
    }

    public IntegerProperty startProperty() {
        return start;
    }

    private boolean dirty = true;

    private ArrayList<Integer> indexes = new ArrayList<>();

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public void setDirection(DIRECTION direction) {
        if (direction != this.direction) {
            this.direction = direction;
            dirty = true;
        }
    }

    public void find(SWITCH sw) {
        find(sw, false);
    }

    public void find(SWITCH sw, boolean replace) {
        if (dirty)
            updateIndexes();
        if (indexes.size() == 0)
            return;
        int shift = sw == SWITCH.NEXT ? 1 : -1;
        int limit = sw == SWITCH.NEXT ? indexes.size() - 1 : 0;
        if (wrapAround.get())
            nowAt = (nowAt + shift) % indexes.size();
        else
            nowAt += nowAt != limit ? shift : 0;
        int index = indexes.get(nowAt);
        int length = query.get().length();
        if (replace) {
            InputHolder.get().text().replaceText(index, index + length, replacer.get());
            length = replacer.get().length();
        }
        InputHolder.get().text().selectRange(index, index + length);
        dirty = false;
    }

    public void replaceAll() {
        String context = InputHolder.get().text().getText();
        context = context.replaceAll(query.get(), replacer.get());
        InputHolder.get().text().setText(context);
    }

    private void updateIndexes() {
        nowAt = -1;
        indexes.clear();
        String context = InputHolder.get().text().getText();
        String query = this.query.get();
        if (!matchCase.get()) {
            context = context.toLowerCase();
            query = query.toLowerCase();
        }
        int index;
        if (direction == DIRECTION.DW) {
            index = context.indexOf(query, start.get());
            while (index != -1) {
                indexes.add(index);
                index = ++index != context.length() ? context.indexOf(query, index) : -1;
            }
            if (wrapAround.get()) {
                index = context.indexOf(query);
                while (index != -1 && index < start.get()) {
                    indexes.add(index);
                    index = context.indexOf(query, index + 1);
                }
            }
        } else {
            index = context.indexOf(query);
            while (index != -1 && index < start.get()) {
                indexes.add(index);
                index = context.indexOf(query, index + 1);
            }
            Collections.reverse(indexes);
            if (wrapAround.get()) {
                ArrayList<Integer> optional = new ArrayList<>();
                index = context.indexOf(query, start.get());
                while (index != -1) {
                    optional.add(index);
                    index = ++index != context.length() ? context.indexOf(query, index) : -1;
                }
                Collections.reverse(optional);
                indexes.addAll(optional);
            }
        }
    }
}