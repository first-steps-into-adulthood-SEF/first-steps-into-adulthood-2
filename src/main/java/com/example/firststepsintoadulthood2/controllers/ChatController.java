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

    public static String userToBeReported;
    public static String postToBeReported;

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
    public ScrollPane convoScrollPane;

    public void initialize() {

        try {

            ChatService.loadMessagesFromFile();

        } catch (IOException e) {
            //e.printStackTrace();
        }
        username.setText(postAuthor);
        VBox vbox = new VBox();
        List<Messages> chats = ChatService.getChats();
        for(Messages ch : chats){
            if(ch.getDestination().equals(keepUsername) && ch.getSource().equals(postAuthor)){
                for(String str : ch.getMessages()){
                    Label username = new Label("@" + keepUsername);
                    Label received = new Label();
                    received.setFont(new Font("Arial", 20));
                    received.setText(str);
                    received.setLayoutX(77);
                    received.setLayoutY(145 + i);
                    received.setFont(Font.font(18));
                    received.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));

                    username.setLayoutX(77);
                    username.setLayoutY(170 + i);

                    i++;

                    vbox.getChildren().addAll(received, username);
                }
            }
            if(ch.getDestination().equals(postAuthor) && ch.getSource().equals(keepUsername)){
                for(String str : ch.getMessages()){
                    Label username = new Label("@" + keepUsername);
                    Label sent = new Label();
                    sent.setText(str);
                    sent.setFont(new Font("Arial", 20));
                    sent.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
                    sent.setLayoutX(77);
                    sent.setLayoutY(145 + i);

                    username.setLayoutX(77);
                    username.setLayoutY(170 + i);

                    i++;

                    vbox.getChildren().addAll(sent, username);
                }
            }
        }
        convoScrollPane.setContent(vbox);
    }

    public void handleSendMessage(ActionEvent actionEvent) throws IOException {

        String auxMsg;

        ChatService.loadMessagesFromFile();
        ChatService.addMessage(keepUsername, postAuthor, messageField.getText());

        if(!messageField.getText().equals("IDWMHA")){

            auxMsg = messageField.getText();

        }

        initialize();

    }


}
