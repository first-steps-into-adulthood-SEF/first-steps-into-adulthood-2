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



import com.example.firststepsintoadulthood2.exceptions.BadLengthFormatException;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWriteUsersException;
import com.example.firststepsintoadulthood2.exceptions.NullFieldsException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.firststepsintoadulthood2.exceptions.UserAlreadyExistsException;
import com.example.firststepsintoadulthood2.services.UserService;



public class RegistrationController {

    @FXML
    private Label registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField birthdayField;


    @FXML
    public void handleRegisterAction() {

        try {

            UserService.addUser(fullNameField.getText(), birthdayField.getText(), usernameField.getText(), passwordField.getText());
            registrationMessage.setText("                          Account created successfully!");

        } catch (UserAlreadyExistsException | CouldNotWriteUsersException | NullFieldsException | BadLengthFormatException e) {

            registrationMessage.setText(e.getMessage());

        }

    }
}