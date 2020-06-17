package com.github.app1echip.notepad.controller.menu;

import java.io.File;
import com.github.app1echip.notepad.service.FileStorageProvider;
import com.github.app1echip.notepad.service.WindowProvider;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FileMenuController {
    private @FXML MenuItem newMenuItem;
    private @FXML MenuItem openMenuItem;
    private @FXML MenuItem saveMenuItem;
    private @FXML MenuItem saveAsMenuItem;
    private @FXML MenuItem exitMenuItem;

    private @FXML void initialize() {
        newMenuItem.setOnAction(e -> {
            if (FileStorageProvider.get().dirty()) {
                switch (FileStorageProvider.get().promptToSave()) {
                    case SAVE:
                        FileStorageProvider.get().save(false);
                        break;
                    case DONT:
                        break;
                    case CANCEL:
                        return;
                }
            }
            FileStorageProvider.get().load(null);
        });
        openMenuItem.setOnAction(e -> {
            if (FileStorageProvider.get().dirty()) {
                switch (FileStorageProvider.get().promptToSave()) {
                    case SAVE:
                        FileStorageProvider.get().save(false);
                        break;
                    case DONT:
                        break;
                    case CANCEL:
                        return;
                }
            }
            File file = new FileChooser().showOpenDialog(new Stage());
            FileStorageProvider.get().load(file);
        });
        saveMenuItem.setOnAction(e -> FileStorageProvider.get().save(false));
        saveAsMenuItem.setOnAction(e -> FileStorageProvider.get().save(true));
        WindowProvider.get().getStage().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
            if (FileStorageProvider.get().dirty()) {
                switch (FileStorageProvider.get().promptToSave()) {
                    case SAVE:
                        FileStorageProvider.get().save(false);
                        break;
                    case DONT:
                        break;
                    case CANCEL:
                        e.consume();
                        return;
                }
            }
        });
        exitMenuItem.setOnAction(e -> WindowProvider.get().getStage()
                .fireEvent(new WindowEvent(WindowProvider.get().getStage(), WindowEvent.WINDOW_CLOSE_REQUEST)));
    }
}