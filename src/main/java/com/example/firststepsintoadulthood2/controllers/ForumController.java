package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.services.PostService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.firststepsintoadulthood2.services.PostService.getPostList;


public class ForumController extends LoginController{

    public void createPostBodyAndAddToHomePage(){

    }

    public void fillWithPosts() throws IOException {
        PostService.loadPostsFromFile();
        createPostBodyAndAddToHomePage();
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


}
