package com.github.app1echip.notepad.controller.menu;

import com.github.app1echip.notepad.service.WindowProvider;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class HelpMenuController {
    private @FXML MenuItem aboutNotepadJMenuItem;

    private @FXML void initialize() {
        aboutNotepadJMenuItem
                .setOnAction(e -> ((HostServices) WindowProvider.get().getStage().getProperties().get("hostServices"))
                        .showDocument("https://github.com/app1echip/NotepadJ"));
    }
}