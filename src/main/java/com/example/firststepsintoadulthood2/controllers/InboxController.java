package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.model.User;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class InboxController extends LoginController{

    public ImageView inboxLogo;
    public Label usernameInInboxPage;
    public ScrollPane scrollPane;
    public ImageView usernamePic;
    public Button returnToForumButton;

    public String getUserIcon(){
        User user = UserService.getUser(keepUsername);
        return user.getImagePath();
    }

    public void returnToForumPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("forum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        usernameInInboxPage.setText(keepUsername);
        if (getUserIcon() != null) {
            usernamePic.setImage(new Image(getUserIcon()));
        }
    }
}
