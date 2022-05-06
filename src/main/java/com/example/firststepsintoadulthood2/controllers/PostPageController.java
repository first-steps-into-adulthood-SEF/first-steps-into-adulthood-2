package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PostPageController extends LoginController{

    public Label postTitleInPage;
    public VBox commentsInPage;
    public Label dateInPage;
    public Label descriptionInPage;
    public Button usernameInPage;

    @FXML
    public void initialize(){
        postTitleInPage.setText(postTitle);
        dateInPage.setText(postDate);
        usernameInPage.setText(postAuthor);
        descriptionInPage.setText(postDescription);
    }

    public void returnToForum(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("forum.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayOptions(ActionEvent actionEvent) {
        AnchorPane root = new AnchorPane();
        Button reportButton = new Button("Report user");
        Button viewProfileButton = new Button("View user profile");
        Button messageButton = new Button("Message user");
        reportButton.setLayoutX(110);
        reportButton.setLayoutY(30);
        reportButton.setCursor(Cursor.HAND);
        reportButton.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        viewProfileButton.setLayoutX(95);
        viewProfileButton.setLayoutY(90);
        viewProfileButton.setCursor(Cursor.HAND);
        viewProfileButton.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        messageButton.setLayoutX(105);
        messageButton.setLayoutY(150);
        messageButton.setCursor(Cursor.HAND);
        messageButton.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(reportButton);
        root.getChildren().add(viewProfileButton);
        root.getChildren().add(messageButton);
        Scene scene = new Scene(root, 300,200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Options");
        stage.show();

        reportButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                Parent root = null;
                try {
                    root = FXMLLoader.load(Main.class.getResource("reportUser.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });
    }
}
