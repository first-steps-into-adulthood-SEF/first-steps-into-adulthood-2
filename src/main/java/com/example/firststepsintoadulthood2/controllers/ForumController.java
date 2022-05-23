package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.exceptions.CouldNotWritePostsException;
import com.example.firststepsintoadulthood2.model.Post;
import com.example.firststepsintoadulthood2.model.User;
import com.example.firststepsintoadulthood2.services.PostService;
import com.example.firststepsintoadulthood2.services.UserService;
import com.fasterxml.jackson.core.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;



public class ForumController extends LoginController{

    @FXML
    public TextField reportedPostTitle;
    @FXML
    public TextField reportedPostAuthor;


    public void switchToInboxPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("inbox.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToForumDescriptionPage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Main.class.getResource("forum-description.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void switchToPostCreationPage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Main.class.getResource("create-post.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void adminDeletePosts(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(Main.class.getResource("delete-posts.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void proceedToDeletePost() throws IOException, CouldNotWritePostsException {

        UserService.loadUsersFromFile();
        PostService.loadPostsFromFile();

        User user = UserService.getUser(reportedPostAuthor.getText());
        String userPost = user.getUsername();

        PostService.deletePostPocedure(userPost, reportedPostTitle.getText());

        //post.removeIf(p -> p.getTitle().equals(reportedPostTitle.getText()) && p.getUsername().equals(Objects.requireNonNull(user).getUsername()));


    }




}
