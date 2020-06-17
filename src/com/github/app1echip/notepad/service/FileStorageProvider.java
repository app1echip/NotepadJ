package com.github.app1echip.notepad.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class FileStorageProvider {
    private static FileStorageProvider instance = new FileStorageProvider();

    public static FileStorageProvider get() {
        return instance;
    }

    private String buffer;
    private File file;
    public String sep = System.lineSeparator();

    public File getFile() {
        return file;
    }

    public boolean dirty() {
        return !InputHolder.get().text().getText().equals(buffer);
    }

    public void setFile(File file) {
        this.file = file;
        TitleProvider.get().update(file);
    }

    public void load(File file) {
        setFile(file);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                int c = reader.read();
                while (c != -1 && c != '\r')
                    c = reader.read();
                if (c == '\r')
                    sep = "\r\n";
            } catch (Exception e) {

            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                buffer = reader.lines().collect(Collectors.joining(sep));
                InputHolder.get().text().setText(buffer);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            buffer = "";
            InputHolder.get().text().clear();
        }
    }

    public void save(File file) {
        setFile(file);
        if (file != null)
            try (FileWriter writer = new FileWriter(file)) {
                buffer = InputHolder.get().text().getText();
                writer.write(buffer);
            } catch (IOException exception) {
                // TODO: handle exception
            }
    }
}