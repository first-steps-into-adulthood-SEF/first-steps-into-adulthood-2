package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.model.Messages;
import com.example.firststepsintoadulthood2.model.User;
import com.example.firststepsintoadulthood2.services.ChatService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InboxController extends LoginController{

    public ImageView inboxLogo;
    public Label usernameInInboxPage;
    public ScrollPane scrollPane;
    public ImageView usernamePic;
    public Button returnToForumButton;

    public void returnToForumPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("forum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Button createConvoBody(Messages ch){

        Button conversation = new Button(ch.getSource());
        conversation.setTextAlignment(TextAlignment.CENTER);
        conversation.setOnAction(event -> {
            Parent convoPage = null;
            try {
                postAuthor = ch.getSource();
                convoPage = FXMLLoader.load(Main.class.getResource("chat.fxml"));
            } catch (IOException e) {
                //e.printStackTrace();
            }
            Stage convoPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene convoPageScene = new Scene(convoPage);
            convoPageStage.setScene(convoPageScene);
            convoPageStage.show();
        });
        conversation.setCursor(Cursor.HAND);
        conversation.setStyle("-fx-text-fill: white; -fx-font-size:15 Calibri");
        conversation.setLayoutX(-300);
        conversation.setPrefWidth(648);
        conversation.setPrefHeight(70);
        if(ch.getSource().equals("SYSTEM")) {
            conversation.setBackground(new Background(new BackgroundFill(Color.web("#772dcc"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else{
            conversation.setBackground(new Background(new BackgroundFill(Color.web("#c8a2c8"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        return conversation;
    }

    public void initialize() {
        usernameInInboxPage.setText(keepUsername);
        User user = UserService.getUser(keepUsername);
        if(user != null){
            String imgP = user.getImagePath();
            if(usernamePic != null){
                usernamePic.setImage(new Image("file:///"+imgP));
            }
        }

        VBox vbox = new VBox();

        try {
            ChatService.loadMessagesFromFile();
            List<Messages> chats = ChatService.getChats();
            ArrayList<String> chatsList = new ArrayList<>();
            for (Messages ch :chats){
                if(ch.getDestination().equals(keepUsername) && !chatsList.contains(ch.getSource())){
                    chatsList.add(ch.getSource());
                    vbox.getChildren().add(createConvoBody(ch));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scrollPane.setContent(vbox);
    }
}
