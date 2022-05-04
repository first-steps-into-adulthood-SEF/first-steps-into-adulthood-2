package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.model.Post;
import com.example.firststepsintoadulthood2.services.PostService;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;



public class ForumController extends LoginController{

    /*@FXML
    public VBox verticalBox;

    public EventHandler<ActionEvent> openPost(Post post){

        return null;
    }

    @FXML
    public void initialize() {

        try {

            fillWithPosts();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    int i = 0, j = 0;

    public void createPostBodyAndAddToHomePage(Post post){

        Pane postPane = new Pane();

        postPane.prefHeight(300);
        postPane.prefWidth(600);

        Button title = new Button(post.getTitle() + "\n*by " + post.getUsername() + ", " + post.getDate());
        title.setOnAction(openPost(post));
        title.setCursor(Cursor.HAND);
        title.setStyle("-fx-font-size:15 Calibri");
        title.setTextAlignment(TextAlignment.LEFT);
        title.setLayoutX(-320);
        title.setPrefWidth(350);
        title.setPrefHeight(70);

        if(i != 0){

            title.setLayoutY(100 + i); i += 100;

        }

        title.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label spatiu= new Label("");
        spatiu.setLayoutY(100 + j);

        postPane.getChildren().add(title);
        postPane.getChildren().add(spatiu);
        verticalBox.getChildren().add(postPane);
        postPane.setLayoutY(1000 + i);


    }

    public void fillWithPosts() throws IOException {

        PostService.loadPostsFromFile();
        Stack<Post> posts = PostService.getPostList();

        for(Post post: posts){

            createPostBodyAndAddToHomePage(post);

        }
    }*/

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

    public void switchToForumPage(ActionEvent event) throws IOException{

        initialize();
        Parent root = FXMLLoader.load(Main.class.getResource("forum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
