package com.github.app1echip.notepad;

import java.io.File;
import java.util.List;

import com.github.app1echip.notepad.Service.FileStorageProvider;
import com.github.app1echip.notepad.Service.StageProvider;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        List<String> args = getParameters().getRaw();
        FileStorageProvider.get().setFile(args.size() != 0 ? new File(args.get(0)) : null);
        FileStorageProvider.get().loadFromFile();
        StageProvider.get().setStage(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Layout/Notepad.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}