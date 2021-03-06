package com.example.firststepsintoadulthood2.controllers;
/*
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegistrationController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Account successfully created.");
    }
}*/



import com.example.firststepsintoadulthood2.LoginMain;
import com.example.firststepsintoadulthood2.exceptions.BadLengthFormatException;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWriteUsersException;
import com.example.firststepsintoadulthood2.exceptions.NullFieldsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.firststepsintoadulthood2.exceptions.UserAlreadyExistsException;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class RegistrationController {

    @FXML
    public Label registrationMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public TextField fullNameField;
    @FXML
    public TextField birthdayField;
    @FXML
    public Button invisible;


    @FXML
    public void handleRegisterAction() {

        try {

            try{
                UserService.addUser(fullNameField.getText(), birthdayField.getText(), usernameField.getText(), passwordField.getText());
                registrationMessage.setText("                      Account created successfully!");
                invisible.setVisible(true);
            }catch(NullPointerException e){

            }
            //UserService.addUser(fullNameField.getText(), birthdayField.getText(), usernameField.getText(), passwordField.getText());
            //registrationMessage.setText("                      Account created successfully!");
            //invisible.setVisible(true);

        } catch (UserAlreadyExistsException | CouldNotWriteUsersException | NullFieldsException | BadLengthFormatException e) {

            registrationMessage.setText(e.getMessage());

        }

    }


    public void switchToLoginPage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(LoginMain.class.getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}