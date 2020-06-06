package com.github.app1echip.notepad;

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Notepad.fxml"));
        Parent root = loader.load();
        List<String> parameters = getParameters().getRaw();
        NotepadController controller = loader.getController();
        controller.stage = stage;
        controller.file = parameters.size() != 0 ? new File(parameters.get(0)) : null;
        controller.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}