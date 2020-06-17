package com.github.app1echip.notepad.controller.prompt;

import com.github.app1echip.notepad.service.FontProvider;
import com.github.app1echip.notepad.service.InputHolder;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FontSelectorController {
    private @FXML TextField fontTextField;
    private @FXML TextField sizeTextField;
    private @FXML ListView<String> fontListView;
    private @FXML ListView<Integer> sizeListView;
    private @FXML Label previewLabel;
    private @FXML Button okayButton;
    private @FXML Button cancelButton;

    private @FXML void initialize() {
        FilteredList<String> fList = new FilteredList<>(FXCollections.observableArrayList(Font.getFamilies()));
        fontListView.setItems(fList);
        ChangeListener<String> listener = (l, o, n) -> {
            for (String face : fList)
                if (n.toLowerCase().equals(face.toLowerCase()))
                    previewLabel.setFont(Font.font(face));
            fList.setPredicate(f -> f.toLowerCase().contains(n.toLowerCase()));
        };
        fontTextField.textProperty().addListener(listener);
        fontListView.getSelectionModel().selectedItemProperty().addListener((l, o, n) -> {
            if (n != null) {
                fontTextField.textProperty().removeListener(listener);
                fontTextField.setText(n);
                previewLabel.setFont(Font.font(n));
                fontTextField.textProperty().addListener(listener);
            }
        });
        ObservableList<Integer> sizeList = FXCollections.observableArrayList(8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24,
                26, 28, 36, 48, 72);
        sizeListView.setItems(sizeList);
        sizeListView.getSelectionModel().selectedItemProperty().addListener((l, o, n) -> {
            Font of = previewLabel.getFont();
            previewLabel.setFont(Font.font(of.getFamily(), FontWeight.findByName(of.getStyle()), n));
            sizeTextField.setText(String.valueOf(n));
        });
        okayButton.setOnAction(e -> {
            for (String face : fList)
                if (fontTextField.getText().toLowerCase().equals(face.toLowerCase()))
                    FontProvider.get().fontFace = face;
            try {
                double inputSize = Double.parseDouble(sizeTextField.getText());
                if (inputSize >= 0)
                    FontProvider.get().fontSize.set(inputSize);
            } catch (Exception exception) {
                // TODO: handle exception
            }
            FontProvider.get().updateFont();
        });
    }

    public void postInitialize() {
        fontTextField.setText(InputHolder.get().text().getFont().getFamily().toString());
        sizeTextField.setText(String.valueOf((int) InputHolder.get().text().getFont().getSize()));
    }
}