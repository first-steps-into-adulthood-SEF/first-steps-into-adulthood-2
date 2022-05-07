package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWritePostsException;
import com.example.firststepsintoadulthood2.model.Post;
import com.example.firststepsintoadulthood2.services.PostService;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PostPageController extends LoginController{

    public Label postTitleInPage;
    public Button usernameInPage;
    public Label descriptionInPage;
    public VBox commentsInPage;
    public Label dateInPage;
    public TextField replyField;
    public Button commentButton;

    @FXML
    public void initialize(){
        postTitleInPage.setText(postTitle);
        dateInPage.setText(postDate);
        usernameInPage.setText(postAuthor);
        descriptionInPage.setText(postDescription);
        //commentsInPage.getChildren().add((Node) postComments);
    }

    public void returnToForum(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("forum.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*public void displayOptions(ActionEvent actionEvent) {
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

        reportButton.setOnAction(event -> {
            Parent root1 = null;
            try {
                root1 = FXMLLoader.load(Main.class.getResource("reportUser.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene1 = new Scene(root1);
            stage1.setScene(scene1);
            stage1.show();
        });
    }*/

    public void addComment(ActionEvent actionEvent) {
        AnchorPane root = new AnchorPane();
        Button yes = new Button("Post reply");
        Button no = new Button("Discard");
        Text text = new Text("Do you want to reply to this?");

        yes.setLayoutX(90);
        yes.setLayoutY(130);
        yes.setCursor(Cursor.HAND);
        yes.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        no.setLayoutX(170);
        no.setLayoutY(130);
        no.setCursor(Cursor.HAND);
        no.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        text.setLayoutX(80);
        text.setLayoutY(100);
        root.getChildren().add(yes);
        root.getChildren().add(no);
        root.getChildren().add(text);
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 300,200);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Confirmation");
        stage.show();


        yes.setOnMousePressed(e->{

            try {

                String replier = keepUsername;
                String reply = replyField.getText();
                int postNumber = PostService.getPostList().search(postTitleInPage) + 1;
                Post post = PostService.getPostList().get(postNumber);
                for(Post p : PostService.getPostList()){
                    if(p.equals(post)){
                        p.addReply(replier, reply);
                    }
                }

            } catch (Exception ex) {

                System.out.println(ex.getMessage());

            }

            stage.close();
        });

        no.setOnMousePressed(e->{

            stage.close();

        });
    }

}
