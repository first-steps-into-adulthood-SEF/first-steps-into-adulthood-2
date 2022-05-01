package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.services.PostService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public TextField usernameField;
    public PasswordField passwordField;
    public Label loginMessage;
    protected static String keepUsername;

    public void switchToRegisterPage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Main.class.getResource("register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToForumPage(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(Main.class.getResource("forum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void LoginButtonClick(ActionEvent event) throws IOException {

        if(UserService.checkCredentials(usernameField.getText(), passwordField.getText())==0){
            loginMessage.setText("User doesn't exist!");
        }
        else if(UserService.checkCredentials(usernameField.getText(), passwordField.getText())==1){
            loginMessage.setText("Incorrect password!");
        }
        else{

            keepUsername = usernameField.getText();
            switchToForumPage(event);
        }

    }

}