package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.exceptions.BadLengthFormatException;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWriteUsersException;
import com.example.firststepsintoadulthood2.exceptions.NullFieldsException;
import com.example.firststepsintoadulthood2.exceptions.UserAlreadyExistsException;
import com.example.firststepsintoadulthood2.services.UserDescriptionsService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.firststepsintoadulthood2.controllers.LoginController.keepUsername;


public class NewProfileDescriptionController{

    @FXML
    public TextField profileDescription;


    public void handleDescriptionChanges() throws IOException, CouldNotWriteUsersException {

        UserService.changeBio(keepUsername, profileDescription.getText());

    }


    public String getProfileBio(){

        return profileDescription.getText();

    }

}
