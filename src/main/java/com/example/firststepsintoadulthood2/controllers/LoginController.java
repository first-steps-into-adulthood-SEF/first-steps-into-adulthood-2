package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.model.Post;
import com.example.firststepsintoadulthood2.services.PostService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;
import java.util.Stack;


public class LoginController {

    public TextField usernameField;
    public PasswordField passwordField;
    public Label loginMessage;
    protected static String keepUsername;


    public void switchToRegisterPage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Main.class.getResource("register.fxml"));
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


    @FXML
    protected void LoginButtonClick(ActionEvent event) throws IOException {

        if(UserService.checkCredentials(usernameField.getText(), passwordField.getText())==0){
            loginMessage.setText("User doesn't exist!");
        }
        else if(UserService.checkCredentials(usernameField.getText(), passwordField.getText())==1){
            loginMessage.setText("Incorrect password!");
        }
        else{

            keepUsername = usernameField.getText();
            switchToForumPage(event);
        }

    }

    @FXML
    public VBox verticalBox;

    @FXML
    public void initialize() {

        try {

            fillWithPosts();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    int i = 0, j = 0;

    @FXML
    public Label postTitleInPage;
    @FXML
    public Label dateInPage;
    @FXML
    public Label descriptionInPage;
    @FXML
    public Button usernameInPage;

    public void setPostProperties(Post post){
        postTitleInPage.setText(post.getTitle());
        usernameInPage.setText(post.getUsername());
        dateInPage.setText(post.getDate());
        descriptionInPage.setText(post.getDescription());
    }

    public void createPostBodyAndAddToHomePage(Post post) throws NullPointerException, IOException {

        Pane postPane = new Pane();

        postPane.prefHeight(300);
        postPane.prefWidth(600);

        Button title = new Button(post.getTitle() + "\n*by " + post.getUsername() + ", " + post.getDate() + "     ");
        title.setTextAlignment(TextAlignment.LEFT);
        title.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                Parent root = null;
                try {
                    root = FXMLLoader.load(Main.class.getResource("postPage.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                setPostProperties(post);
            }
        });
        title.setCursor(Cursor.HAND);
        title.setStyle("-fx-font-size:15 Calibri");
        title.setLayoutX(-300);
        title.setPrefWidth(380);
        title.setPrefHeight(70);

        if(i != 0){

            title.setLayoutY(100 + i); i += 100;

        }

        title.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label space= new Label("");
        space.setLayoutY(75 + j);

        Image img = new Image("av30af0a6ffb584d0e709.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20); view.setFitWidth(20);
        view.setLayoutX(43); view.setLayoutY(300 + i);

        Button flag = new Button();
        flag.setGraphic(view);
        flag.setCursor(Cursor.HAND);
        flag.setPrefHeight(20); flag.setPrefWidth(20);
        flag.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        flag.setLayoutX(43); /*flag.setLayoutY(400 + i);*/


        postPane.getChildren().add(title);
        postPane.getChildren().add(space);
        postPane.getChildren().add(flag);


        try{

            verticalBox.getChildren().add(postPane);

        }catch(NullPointerException e){

            e.printStackTrace();

        }

        postPane.setLayoutY(1000 + i);

    }

    public void fillWithPosts() throws IOException {

        PostService.loadPostsFromFile();
        Stack<Post> posts = PostService.getPostList();

        for(Post post: posts){

            createPostBodyAndAddToHomePage(post);

        }
    }

    public void returnToForum(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("forum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}