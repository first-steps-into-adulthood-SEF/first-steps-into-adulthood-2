package com.example.firststepsintoadulthood2.controllers;

import com.example.firststepsintoadulthood2.Main;
import com.example.firststepsintoadulthood2.exceptions.*;
import com.example.firststepsintoadulthood2.model.Post;
import com.example.firststepsintoadulthood2.model.User;
import com.example.firststepsintoadulthood2.services.PostService;
import com.example.firststepsintoadulthood2.services.UserService;
import javafx.css.converter.CursorConverter;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.example.firststepsintoadulthood2.controllers.LoginController.keepUsername;
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
        Text text = new Text("Do you want to post this?");

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

                java.util.Date date = new java.util.Date();

                PostService.addPosts(titleField.getText(), descriptionField.getText(), keepUsername, String.valueOf(date));

            } catch (CouldNotWritePostsException ex) {

                System.out.println(ex.getMessage());

            }

            stage.close();
        });





        no.setOnMousePressed(e->{

            stage.close();

        });

    }

    public void banUser(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("banForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
