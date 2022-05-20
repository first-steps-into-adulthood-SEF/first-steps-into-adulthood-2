package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWriteUsersException;
import com.example.firststepsintoadulthood2.services.BannedUsersService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BanFormController extends LoginController{


    public Button cancelButton;
    public Button banButton;
    public Label userToBeBanned;
    public Label inputWarning;
    public TextField banPeriod;
    public TextField banDescription;

    @Override
    public void initialize() {

    }

    public void closeBanForm(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void banUser(ActionEvent actionEvent) {
        int daysNr = -1;
        try {
            String period = banPeriod.getText();
            daysNr = Integer.parseInt(period);
        }catch (Exception e){
            inputWarning.setText("Please enter a valid value!");
        }
    }
}
