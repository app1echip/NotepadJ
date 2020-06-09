package com.github.app1echip.notepad.Controller.Prompt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class FontSelectorController {
    private @FXML TextField fontTextField;
    private @FXML TextField fontStyleTextField;
    private @FXML TextField sizeTextField;
    private @FXML ListView<String> fontListView;
    private @FXML ListView<String> fontStyleListView;
    private @FXML ListView<Integer> sizeListView;
    private ObservableList<String> fontList = FXCollections.observableArrayList();
    private ObservableList<String> fontStyleList = FXCollections.observableArrayList();
    private ObservableList<Integer> sizeList = FXCollections.observableArrayList();

    private @FXML void initialize() {
        fontList.addAll(Font.getFamilies());
        fontListView.setItems(fontList);
        fontListView.getSelectionModel().selectedItemProperty().addListener((l, o, n) -> fontTextField.setText(n));
        fontStyleListView.setItems(fontStyleList);
        sizeListView.setItems(sizeList);

    }
}