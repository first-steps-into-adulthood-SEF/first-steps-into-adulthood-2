package com.example.firststepsintoadulthood2.controllers;


import com.example.firststepsintoadulthood2.exceptions.CouldNotWritePostsException;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWriteUsersException;
import com.example.firststepsintoadulthood2.services.ReportedPostsService;
import com.example.firststepsintoadulthood2.services.ReportedUsersService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class UserReportController extends LoginController{

    public TextField reportDescription;
    public Label reportedUserUsername;
    public Button sendReportButton;
    public Button closeReportFormButton;
    public CheckBox languageCheckbox;
    public CheckBox themeCheckbox;
    public CheckBox stoleCheckbox;

    public void sendUserReport(ActionEvent actionEvent) throws CouldNotWriteUsersException, IOException {
        ReportedUsersService.loadReportedUsersFromFile();
        ArrayList<String> option = new ArrayList<>();
        if(languageCheckbox.isSelected()){
            option.add("bad language");
        }
        if(themeCheckbox.isSelected()){
            option.add("theme not respected");
        }
        if(stoleCheckbox.isSelected()){
            option.add("stolen identity");
        }
        ReportedUsersService.addReportedUsers(keepUsername, ReportedUsersService.rememberUser, String.valueOf(option), reportDescription.getText());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void closeReportForm(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
