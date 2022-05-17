package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.model.Post;
import com.example.firststepsintoadulthood2.services.PostService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class PostPageController extends LoginController{

    public Label postTitleInPage;
    public Button usernameInPage;
    public Label descriptionInPage;
    public Label dateInPage;
    public TextField replyField;
    public Button commentButton;
    public VBox repliesVBox;

    public Label createReplyBody(String reply){
        Label replyBody = new Label(reply);
        replyBody.setBackground(new Background(new BackgroundFill(Color.rgb(200, 162, 200), CornerRadii.EMPTY, Insets.EMPTY)));
        replyBody.setFont(new Font("Cambria", 15));
        return replyBody;
    }

    public void showReplies(){
        repliesVBox.setSpacing(8);
        Post targetPost = null;
        Stack<Post> postList = PostService.getPostList();
        for(Post p : postList){
            if(p.getTitle().equals(postTitleInPage.getText())){
                targetPost = p;
            }
        }
        if (targetPost != null) {
            for(String reply : targetPost.getReplies()){
                repliesVBox.getChildren().add(createReplyBody(reply));
            }
        }
    }

    @FXML
    public void initialize(){
        postTitleInPage.setText(postTitle);
        dateInPage.setText(postDate);
        usernameInPage.setText(postAuthor);
        descriptionInPage.setText(postDescription);
        showReplies();
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
        Button postReply = new Button("Post reply");
        Button discard = new Button("Discard");
        Text text = new Text("Do you want to reply to this?");

        postReply.setLayoutX(90);
        postReply.setLayoutY(130);
        postReply.setCursor(Cursor.HAND);
        postReply.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        discard.setLayoutX(170);
        discard.setLayoutY(130);
        discard.setCursor(Cursor.HAND);
        discard.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        text.setLayoutX(80);
        text.setLayoutY(100);
        root.getChildren().add(postReply);
        root.getChildren().add(discard);
        root.getChildren().add(text);
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 300,200);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Confirmation");
        stage.show();


        postReply.setOnMousePressed(e->{

            try {

                String replier = keepUsername;
                String reply = replyField.getText();
                Stack<Post> postList = PostService.getPostList();
                for(Post p : postList){
                    if(p.getTitle().equals(postTitleInPage.getText())){
                        p.setTitle("prank");
                    }
                }

            } catch (Exception ex) {

                System.out.println(ex.getMessage());

            }

            stage.close();
        });

        discard.setOnMousePressed(e->{
            replyField.setText("");
            stage.close();
        });
    }

}
