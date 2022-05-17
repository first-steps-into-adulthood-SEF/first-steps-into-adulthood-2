package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.services.ChatService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class ChatController extends LoginController{

    @FXML
    public TextField messageField;
    @FXML
    public Label username;
    @FXML
    public Label usernameTxt;
    @FXML
    public Label message;

    public int i = 0;


    public void initialize(){

        username.setText(postAuthor);

    }

    public void handleSendMessage(ActionEvent actionEvent) throws IOException {

        ChatService.loadMessagesFromFile();
        ChatService.addMessage(keepUsername, postAuthor, messageField.getText());
        usernameTxt.setText("@" + keepUsername);
        message.setText(messageField.getText());
        usernameTxt.setVisible(true);
        message.setVisible(true);
    }

}
