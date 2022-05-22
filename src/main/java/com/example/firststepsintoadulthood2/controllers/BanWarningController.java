package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.services.BannedUsersService;
import javafx.scene.control.Label;

import java.io.IOException;

public class BanWarningController extends LoginController{
    public Label banReasonLabel;
    public Label daysRemaining;

    public void initialize(){
        try {
            banReasonLabel.setText(BannedUsersService.getBanDescription(keepUsername));
            daysRemaining.setText("" + (double) BannedUsersService.checkBanned(keepUsername) /86400000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
