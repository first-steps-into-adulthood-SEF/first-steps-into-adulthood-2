package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.model.Messages;
import com.example.firststepsintoadulthood2.services.ChatService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class ChatController extends LoginController{

    @FXML
    public TextField messageField;
    @FXML
    public Label username;
    @FXML
    public Label usernameTxt;
    @FXML
    public Label message;
    @FXML
    public Pane pane;

    public int i = 1;

    public void initialize() {

        try {

            ChatService.loadMessagesFromFile();

        } catch (IOException e) {
            //e.printStackTrace();
        }
        username.setText(postAuthor);

    }

    public void handleSendMessage(ActionEvent actionEvent) throws IOException {

        String auxMsg;

        ChatService.loadMessagesFromFile();
        ChatService.addMessage(keepUsername, postAuthor, messageField.getText());

        if(!messageField.getText().equals("IDWMHA")){

            auxMsg = messageField.getText();
            sendMessage(auxMsg);

        }

    }


    public void sendMessage(String msg){

        Pane msgPane = new Pane();

        msgPane.prefHeight(900);
        msgPane.prefWidth(900);

        Character[] pieces = new Character[1000];
        int k = 0, l = 1;

        for(int i = 0; i < msg.length(); i++){

            pieces[k++] = msg.charAt(i);

            if(i > 50 * l && msg.charAt(i) == ' '){

                pieces[k++] = '\n';
                l *= 2;

            }

        }

        StringBuilder str = new StringBuilder();

        for(int i = 0; i < k; i++){

            str.append(pieces[i]);

        }

        Label text = new Label(str + "          ");
        Label username = new Label("@" + keepUsername);

        text.setLayoutX(77);
        text.setLayoutY(145 + i);
        text.setFont(Font.font(18));
        text.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));

        username.setLayoutX(77);
        username.setLayoutY(170 + i + l*16);

        i += 100;

        msgPane.getChildren().add(text);
        msgPane.getChildren().add(username);
        pane.getChildren().add(msgPane);

    }

}
