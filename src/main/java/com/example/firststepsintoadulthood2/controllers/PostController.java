package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.exceptions.*;
import com.example.firststepsintoadulthood2.model.Post;
import com.example.firststepsintoadulthood2.services.PostService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.example.firststepsintoadulthood2.services.PostService.getPostList;
import static com.example.firststepsintoadulthood2.services.PostService.loadPostsFromFile;

public class PostController extends ForumController{

    public static TextArea textArea = new TextArea();

    @FXML
    private Button confirmationButton;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;


    @FXML
    public void handleSaveChangesAction() throws IOException {

        loadPostsFromFile();

        confirmationButton.setOnAction(e->{
            popUpWindow();
        });


    }


    public void popUpWindow(){

        AnchorPane root = new AnchorPane();
        Button yes = new Button("Yes");
        Button no = new Button("No");
        Text text = new Text("Do you want to publish this post?");

        yes.setLayoutX(90);
        yes.setLayoutY(130);
        yes.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        no.setLayoutX(170);
        no.setLayoutY(130);
        no.setBackground(new Background(new BackgroundFill(Color.web("#C8A2C8"), CornerRadii.EMPTY, Insets.EMPTY)));
        text.setLayoutX(60);
        text.setLayoutY(100);
        root.getChildren().add(yes);
        root.getChildren().add(no);
        root.getChildren().add(text);
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 300,200);
        scene.setFill(Color.RED);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Confirmation");
        stage.show();

        yes.setOnAction(e->{

            try {

                PostService.addPosts(titleField.getText(), descriptionField.getText(), keepUsername);


            } catch (CouldNotWritePostsException ex) {

                System.out.println(ex.getMessage());

            }


            stage.close();
        });


        no.setOnAction(e->{

            stage.close();

        });

    }


}
