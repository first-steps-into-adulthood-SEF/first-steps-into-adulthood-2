package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWriteUsersException;
import com.example.firststepsintoadulthood2.services.BannedUsersService;
import com.example.firststepsintoadulthood2.services.ReportedUsersService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

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

    public void banUser(ActionEvent actionEvent) throws IOException, CouldNotWriteUsersException {
        BannedUsersService.loadBannedUsersFromFile();
        int daysNr = -1;
        try {
            String period = banPeriod.getText();
            daysNr = Integer.parseInt(period);
        }catch (Exception e){
            inputWarning.setText("Please enter a valid value!");
        }
        if(daysNr != -1){
            BannedUsersService.addBannedUser(ReportedUsersService.rememberUser, banDescription.getText(), daysNr);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
