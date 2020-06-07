package com.github.app1echip.notepad;

import java.io.File;
import java.util.List;

import com.github.app1echip.notepad.Controller.NotepadController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Layout/Notepad.fxml"));
        Parent root = loader.load();
        List<String> args = getParameters().getRaw();
        NotepadController controller = loader.getController();
        controller.postInitialize(stage, args.size() != 0 ? new File(args.get(0)) : null);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}