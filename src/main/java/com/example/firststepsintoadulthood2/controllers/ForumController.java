package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.model.Post;
import com.example.firststepsintoadulthood2.services.PostService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;
import java.util.Stack;


public class ForumController extends LoginController{

    @FXML
    public VBox verticalBox;

    public EventHandler<ActionEvent> openPost(Post post){

        return null;
    }

    public void createPostBodyAndAddToHomePage(Post post){
        Pane panouPostare = new Pane();
        panouPostare.prefHeight(300);
        panouPostare.prefWidth(600);
        Button titlu = new Button(post.getTitle());
        titlu.setOnAction(openPost(post));
        titlu.setCursor(Cursor.HAND);
        titlu.setStyle("-fx-font-size:15 Cambria");
        Label descriere = new Label(post.getDescription());
        panouPostare.getChildren().add(titlu);
        panouPostare.getChildren().add(descriere);
        verticalBox.getChildren().add(panouPostare);
    }

    public void fillWithPosts() throws IOException {
        PostService.loadPostsFromFile();
        Stack<Post> posts = PostService.getPostList();
        for(Post post: posts){
            createPostBodyAndAddToHomePage(post);
        }
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
