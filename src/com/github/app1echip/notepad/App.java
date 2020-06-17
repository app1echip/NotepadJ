package com.github.app1echip.notepad;

import java.io.File;
import java.util.List;
import com.github.app1echip.notepad.service.FileStorageProvider;
import com.github.app1echip.notepad.service.WindowProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        WindowProvider.get().register(stage);
        stage.getProperties().put("hostServices", this.getHostServices());
        List<String> args = getParameters().getRaw();
        FileStorageProvider.get().setFile(args.size() != 0 ? new File(args.get(0)) : null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Layout/Notepad.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}