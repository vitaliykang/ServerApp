package com.example.serverapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Getter;

public class MainController {
    @FXML
    @Getter
    private TextArea textArea;

    @FXML
    private TextField textField;

    @FXML
    void sendMessage(ActionEvent event) {
        String message = textField.getText();
        ServerConnection.send(message);
        updateLog(message);
    }

    public void updateLog(String message) {
        StringBuilder sb = new StringBuilder(textArea.getText());
        sb.append("\n").append(message);
        textArea.setText(sb.toString());
    }
}