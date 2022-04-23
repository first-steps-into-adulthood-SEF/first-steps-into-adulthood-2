package com.example.firststepsintoadulthood2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegistrationController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Account successfully created.");
    }
}