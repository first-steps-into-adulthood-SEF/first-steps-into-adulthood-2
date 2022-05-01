package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.model.Post;
import com.example.firststepsintoadulthood2.services.PostService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

import static com.example.firststepsintoadulthood2.services.PostService.getPostList;
import static com.example.firststepsintoadulthood2.services.PostService.loadPostsFromFile;

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

        Parent root = FXMLLoader.load(Main.class.getResource("forum.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        createFeed();

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


    public void createFeed() throws IOException {

        loadPostsFromFile();

        Stack<Post> auxPosts = getPostList();

        TextArea txtArea = new TextArea();

        /*AnchorPane rootPost = new AnchorPane();
        double defaultX = 150.0f;
        double defaultY = 75.0f;
        rootPost.setVisible(true);*/

        while (auxPosts.size() > 0){

            Post post = auxPosts.peek();

            System.out.println(post.getTitle() + "---" + post.getDescription());

            /*Rectangle rectangle = new Rectangle();

            rectangle.setX(defaultX);
            rectangle.setY(defaultY + 20);
            rectangle.setWidth(300.0f);
            rectangle.setHeight(50.0f);

            rectangle.setArcWidth(30.0);
            rectangle.setArcHeight(20.0);

            rectangle.setFill(Color.web("#C8A2C8"));
            rectangle.setVisible(true);

            Text title = new Text(post.getTitle());
            Text user = new Text("by" + keepUsername);

            title.setX(defaultX + 5);
            title.setY(defaultY + 25);
            title.setVisible(true);

            user.setX(defaultX + 5);
            user.setY(defaultY + 30);
            user.setVisible(true);


            rootPost.getChildren().addAll(rectangle, title, user);*/

            txtArea.appendText(post.getTitle() + post.getDescription() + " \n");

            auxPosts.pop();

        }

    }

}